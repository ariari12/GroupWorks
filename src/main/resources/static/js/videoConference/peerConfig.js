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

document.addEventListener('DOMContentLoaded', async ()=>{
    roomId = document.querySelector('#roomId').value;

    // 카메라 시작
    await startCam();

    // 소켓 연결 시작
    await connectSocket();

    await startConferenceHandler()

})
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

const startConferenceHandler = async () => {
    console.log('Starting conference');
    stompClient.send(`/app/call/key`, {}, {});
    setTimeout(() => {
        otherKeyList.forEach((key) => {
            if (!pcListMap.has(key)) {
                pcListMap.set(key, createPeerConnection(key));
                sendOffer(pcListMap.get(key), key);
            } else {
                sendOffer(pcListMap.get(key), key);
            }
        });
    }, 1000);
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
    const socket = new SockJS('/signaling');
    stompClient = Stomp.over(socket);
    stompClient.debug = (str) => {
        console.log('STOMP: ' + str);
    };

    const onConnect = () => {
        console.log('Connected to WebRTC server');
        retryCount = 0; // Reset retry count on successful connection
        setupSubscriptions();
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
        }
    };

    const onDisconnect = () => {
        alert('화상회의가 종료되었습니다.');
        window.location.href = '/main'; // 모든 사용자 리다이렉트
    };

    stompClient.connect({}, onConnect, onError);

    // STOMP 연결이 끊어질 때 호출되는 함수 설정
    stompClient.onclose = onDisconnect;
};

// WebSocket 구독 설정 함수
const setupSubscriptions = () => {
    stompClient.subscribe(`/topic/peer/iceCandidate/${myKey}/${roomId}`, candidate => {
        const key = JSON.parse(candidate.body).key;
        const message = JSON.parse(candidate.body).body;
        console.log(`Received ICE candidate from ${key}:`, message);
        if (pcListMap.has(key)) {
            pcListMap.get(key).addIceCandidate(new RTCIceCandidate(message)).catch(error => {
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

    // 종료 메시지를 수신하여 처리하는 부분
    stompClient.subscribe(`/topic/peer/endConference/${roomId}`, message => {
        console.log('Received end conference message:', message);
        // 상대방이 종료했을 때 remoteStreamDiv 숨기기
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
        video.style.width = '640px';   // 크기 설정
        video.style.height = '360px';  // 크기 설정
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


function disconnect(){
    if(stompClient !== null){
        stompClient.disconnect(function (){
            console.log("Disconnected");
        })
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


// 종료하기 버튼 이벤트 리스너를 새로운 종료하기 버튼에 추가
document.querySelector('#endConferenceBtn').addEventListener('click', async () => {
    handleEndConference();
    sendEndConferenceMessage(); // 종료 메시지를 보냄
    if(localStream.getVideoTracks()[0].enabled){
        localStream.getVideoTracks()[0].enabled = !localStream.getVideoTracks()[0].enabled;
    }
    if(localStream.getAudioTracks()[0].enabled){
        localStream.getAudioTracks()[0].enabled = !localStream.getAudioTracks()[0].enabled;
    }
    document.querySelector('#localStream').style.display = 'none'; // 내 video 컴포넌트를 숨김
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