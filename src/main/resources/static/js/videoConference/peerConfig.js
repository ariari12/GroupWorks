let localStreamElement = document.querySelector('#localStream');
const myKey = Math.random().toString(36).substring(2, 11);
let pcListMap = new Map();
let roomId;
let otherKeyList = [];
let localStream = undefined;
let stompClient;
const maxRetries = 3;
let retryCount = 0;
const existingRoomIds = [];

let screenStream = null; // 화면 공유를 위한 스트림

let allClientsReady = false;
let connectedClients = [];

document.addEventListener('DOMContentLoaded', async () => {
    console.log("DOMContentLoaded event fired");
    try {
        console.log("Script Initialization Started");
        roomId = document.querySelector('#roomId').value;
        await startCam();
        await connectSocket(); // WebSocket 연결 시작
        console.log("Script Initialization Completed");
    } catch (error) {
        console.error("Error during initialization:", error);
    }
});

// 방에 입장한 클라이언트가 모두 준비되었는지 확인
const checkIfAllClientsReady = () => {
    if (connectedClients.length >= 2) {  // 최소 두 명의 클라이언트가 준비되었는지 확인
        allClientsReady = true;
        startConferenceHandler();  // WebRTC 연결 시작
    }
};

// 클라이언트가 방에 입장했음을 서버에 알림
const notifyServerOfEntry = () => {
    stompClient.send(`/app/peer/entered/${roomId}`, {}, JSON.stringify({
        key: myKey
    }));
};

// 카메라 시작 함수
const startCam = async () => {
    try {
        const stream = await navigator.mediaDevices.getUserMedia({ audio: true, video: true });
        console.log('Stream found');
        localStream = stream;
        stream.getAudioTracks()[0].enabled = true;
        localStreamElement.srcObject = localStream;
        localStreamElement.style.display = 'block';
    } catch (error) {
        console.error("Error accessing media devices:", error);
        handleMediaError(error);
    }
};

// WebRTC 연결 시작 함수
const startConferenceHandler = async () => {
    if (allClientsReady) {
        if (!pcListMap.has(myKey)) {
            // 첫 번째 클라이언트가 Offer를 보냄
            if (connectedClients[0] === myKey) {
                console.log('Sending initial offer');
                pcListMap.set(myKey, createPeerConnection(myKey));
                sendOffer(pcListMap.get(myKey), connectedClients[1]);
            }
        }
    }
};

// 미디어 오류 처리 함수
const handleMediaError = async (error) => {
    console.error("Media error: ", error);
    displayBlackScreen();
};

// 검은 화면 표시 함수
const displayBlackScreen = () => {
    localStreamElement.classList.add('black-screen');
    localStreamElement.style.display = 'block';
};

// 소켓 연결 함수
const connectSocket = async () => {
    return new Promise((resolve, reject) => {
        const socket = new SockJS('/signaling');
        stompClient = Stomp.over(socket);
        stompClient.debug = (str) => {
            console.log('STOMP: ' + str);
        };

        const onConnect = () => {
            console.log('Connected to WebRTC server');
            retryCount = 0; // Reset retry count on successful connection
            setupSubscriptions();
            notifyServerOfEntry();  // 연결 후 notifyServerOfEntry 호출
            resolve();
        };

        const onError = (error) => {
            console.error('Connection error:', error);
            if (retryCount < maxRetries) {
                retryCount++;
                console.log(`Reconnecting... Attempt ${retryCount}/${maxRetries}`);
                setTimeout(connectSocket, 2000); // Retry connection after 2 seconds
            } else {
                console.error('Max retry attempts reached. Could not connect to WebRTC server.');
                alert('화상회의가 종료되었습니다.');
                window.location.href = '/main'; // 모든 사용자 리다이렉트
                reject(error);
            }
        };

        const onDisconnect = () => {
            alert('화상회의가 종료되었습니다.');
            window.location.href = '/main'; // 모든 사용자 리다이렉트
        };

        stompClient.connect({}, onConnect, onError);

        // STOMP 연결이 끊어질 때 호출되는 함수 설정
        stompClient.onclose = onDisconnect;
    });
};

// WebSocket 구독 설정 함수
const setupSubscriptions = () => {
    stompClient.subscribe(`/topic/peer/iceCandidate/${myKey}/${roomId}`, candidate => {
        const key = JSON.parse(candidate.body).key;
        const message = JSON.parse(candidate.body).body;
        console.log(`Received ICE candidate from ${key}:`, message);
        if (pcListMap.has(key)) {
            pcListMap.get(key).addIceCandidate(new RTCIceCandidate(message)).then(() => {
                console.log(`ICE candidate added from ${key}`);
            }).catch(error => {
                console.error('Error adding received ICE candidate:', error);
            });
        } else {
            console.warn(`No peer connection for key ${key}`);
        }
    });

    stompClient.subscribe(`/topic/peer/offer/${myKey}/${roomId}`, offer => {
        const key = JSON.parse(offer.body).key;
        const message = JSON.parse(offer.body).body;
        console.log(`Received offer from ${key}`);
        if (!pcListMap.has(key)) {
            pcListMap.set(key, createPeerConnection(key));
        }
        pcListMap.get(key).setRemoteDescription(new RTCSessionDescription(message)).then(() => {
            sendAnswer(pcListMap.get(key), key);
            console.log(`Sent answer to ${key}`);
        }).catch(error => {
            console.error('Error setting remote description:', error);
        });
    });

    stompClient.subscribe(`/topic/peer/answer/${myKey}/${roomId}`, answer => {
        const key = JSON.parse(answer.body).key;
        const message = JSON.parse(answer.body).body;
        console.log(`Received answer from ${key}`);
        if (pcListMap.has(key)) {
            pcListMap.get(key).setRemoteDescription(new RTCSessionDescription(message))
                .catch(error => {
                    console.error('Error setting remote description:', error);
                });
        } else {
            console.warn(`No peer connection for key ${key}`);
        }
    });

    stompClient.subscribe(`/topic/call/key`, message => {
        console.log('Received call key message');
        stompClient.send(`/app/send/key`, {}, JSON.stringify(myKey));
    });

    stompClient.subscribe(`/topic/send/key`, message => {
        const key = JSON.parse(message.body);
        console.log(`Received send key: ${key}`);
        if (myKey !== key && !otherKeyList.includes(key)) {
            otherKeyList.push(key);
        }
    });

    stompClient.subscribe(`/topic/peer/entered/${roomId}`, message => {
        const key = JSON.parse(message.body).key;
        if (!connectedClients.includes(key)) {
            connectedClients.push(key);
            checkIfAllClientsReady();
        }
    });

    // 종료 메시지를 수신하여 처리하는 부분
    stompClient.subscribe(`/topic/peer/endConference/${roomId}`, message => {
        console.log('Received end conference message:', message);
        document.querySelector('#remoteStreamDiv').style.display = 'none';
        alert('상대방이 화상회의를 종료했습니다.');
        window.location.href = '/main'; // 모든 사용자 리다이렉트
    });
};

// 원격 트랙 수신 함수
let onTrack = (event, otherKey) => {
    console.log(`Received remote track from ${otherKey}`);
    const remoteVideo = document.getElementById(`${otherKey}`);
    if (remoteVideo) {
        remoteVideo.srcObject = event.streams[0];
    } else {
        const video = document.createElement('video');
        video.autoplay = true;
        video.controls = true;
        video.id = otherKey;
        video.srcObject = event.streams[0];
        video.style.width = '640px';
        video.style.height = '360px';
        document.getElementById('remoteStreamDiv').appendChild(video);
    }
};

// PeerConnection 생성 함수
const createPeerConnection = (otherKey) => {
    const pc = new RTCPeerConnection({
        iceServers: [
            { urls: 'stun:stun.l.google.com:19302' }
        ]
    });
    try {
        pc.addEventListener('icecandidate', (event) => {
            onIceCandidate(event, otherKey);
        });
        pc.addEventListener('track', (event) => {
            onTrack(event, otherKey);
        });
        if (localStream !== undefined) {
            localStream.getTracks().forEach(track => {
                pc.addTrack(track, localStream);
            });
        }
        console.log(`PeerConnection created for ${otherKey}`);
    } catch (error) {
        console.error('PeerConnection failed: ', error);
    }
    return pc;
};

// ICE 후보 처리 함수
let onIceCandidate = (event, otherKey) => {
    if (event.candidate) {
        console.log('Sending ICE candidate:', event.candidate);
        stompClient.send(`/app/peer/iceCandidate/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: event.candidate
        }));
    }
};

// Offer 보내는 함수
let sendOffer = (pc, otherKey) => {
    pc.createOffer().then(offer => {
        setLocalAndSendMessage(pc, offer);
        stompClient.send(`/app/peer/offer/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: offer
        }));
        console.log('Send offer');
    });
};

// Answer 보내는 함수
let sendAnswer = (pc, otherKey) => {
    pc.createAnswer().then(answer => {
        setLocalAndSendMessage(pc, answer);
        stompClient.send(`/app/peer/answer/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: answer
        }));
        console.log('Send answer');
    });
};

// 로컬 설명 설정 및 메시지 전송 함수
const setLocalAndSendMessage = (pc, sessionDescription) => {
    pc.setLocalDescription(sessionDescription).then(() => {
        console.log('Set local description:', sessionDescription);
    }).catch(error => {
        console.error('Error setting local description:', error);
    });
};

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect(function () {
            console.log("Disconnected");
        });
    }
}

// 종료 메시지를 보내는 함수
const sendEndConferenceMessage = () => {
    stompClient.send(`/app/peer/endConference/${roomId}`, {}, JSON.stringify({
        content: "GoodBye!"
    }));
};

// 화상회의 종료 함수
const handleEndConference = async () => {
    console.log('Sending end conference message');
    setTimeout(() => {
        stompClient.disconnect(() => {
            console.log("disconnected");
        });
    }, 1000);
    disconnect();
    alert('화상회의가 종료되었습니다.'); // 직접 종료 사용자에게도 알림
    window.location.href = '/main';
};

// 종료하기 버튼 이벤트 리스너 추가
document.querySelector('#endConferenceBtn').addEventListener('click', async () => {
    handleEndConference();
    sendEndConferenceMessage(); // 종료 메시지를 보냄
    if (localStream.getVideoTracks()[0].enabled) {
        localStream.getVideoTracks()[0].enabled = !localStream.getVideoTracks()[0].enabled;
    }
    if (localStream.getAudioTracks()[0].enabled) {
        localStream.getAudioTracks()[0].enabled = !localStream.getAudioTracks()[0].enabled;
    }
    document.querySelector('#localStream').style.display = 'none';
});

// 카메라 끄기/켜기 버튼 이벤트 리스너
document.querySelector('#toggleCameraBtn').addEventListener('click', () => {
    if (localStream) {
        localStream.getVideoTracks()[0].enabled = !localStream.getVideoTracks()[0].enabled;
        document.querySelector('#toggleCameraBtn').textContent =
            localStream.getVideoTracks()[0].enabled ? '카메라 끄기' : '카메라 켜기';
    }
});

// 마이크 끄기/켜기 버튼 이벤트 리스너
document.querySelector('#toggleMicBtn').addEventListener('click', () => {
    if (localStream) {
        localStream.getAudioTracks()[0].enabled = !localStream.getAudioTracks()[0].enabled;
        document.querySelector('#toggleMicBtn').textContent =
            localStream.getAudioTracks()[0].enabled ? '마이크 끄기' : '마이크 켜기';
    }
});

// 화면 공유를 시작하는 함수
const startScreenShare = async () => {
    try {
        screenStream = await navigator.mediaDevices.getDisplayMedia({ video: true });
        console.log('Screen share started');

        // 기존 비디오 트랙을 화면 공유 트랙으로 교체
        pcListMap.forEach(pc => {
            // 기존의 모든 비디오 트랙 제거
            const senders = pc.getSenders();
            senders.forEach(sender => {
                if (sender.track && sender.track.kind === 'video') {
                    pc.removeTrack(sender);
                }
            });

            // 화면 공유 트랙 추가
            screenStream.getTracks().forEach(track => {
                pc.addTrack(track, screenStream);
            });
        });

        // 화면 공유 비디오를 로컬 화면에 표시
        localStreamElement.srcObject = screenStream;
        localStreamElement.style.display = 'block';
    } catch (error) {
        console.error("Error starting screen share:", error);
        alert("화면 공유를 시작할 수 없습니다.");
    }
};

// 화면 공유를 종료하는 함수
const stopScreenShare = () => {
    if (screenStream) {
        screenStream.getTracks().forEach(track => track.stop());
        screenStream = null;
        console.log('Screen share stopped');

        // 기존의 비디오 트랙 복구
        pcListMap.forEach(pc => {
            // 기존의 모든 비디오 트랙 제거
            const senders = pc.getSenders();
            senders.forEach(sender => {
                if (sender.track && sender.track.kind === 'video') {
                    pc.removeTrack(sender);
                }
            });

            // 로컬 비디오 트랙 추가
            localStream.getTracks().forEach(track => {
                if (track.kind === 'video') {
                    pc.addTrack(track, localStream);
                }
            });
        });

        // 기존 웹캠 비디오를 로컬 화면에 다시 표시
        localStreamElement.srcObject = localStream;
        localStreamElement.style.display = 'block';
    }
};

// 화면 공유 버튼 이벤트 리스너
document.querySelector('#toggleScreenShareBtn').addEventListener('click', () => {
    if (screenStream) {
        stopScreenShare();
        document.querySelector('#toggleScreenShareBtn').textContent = '화면 공유 켜기';
    } else {
        startScreenShare().then(() => {
            document.querySelector('#toggleScreenShareBtn').textContent = '화면 공유 끄기';
        });
    }
});

// 종료하기 및 카메라끄기 버튼 표시
document.getElementById('controlButtons').style.display = 'block';
