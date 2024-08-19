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


// 방 ID를 서버와 동기화하는 함수
const syncRoomIdsWithServer = async () => {
    try {
        // 서버에서 방 ID 목록을 가져오는 API 호출
        const response = await fetch('/videoConference/rooms');
        const data = await response.json();
        existingRoomIds.length = 0;  // 기존 방 ID 목록 초기화
        existingRoomIds.push(...data);
    } catch (error) {
        console.error("Failed to sync room IDs with server:", error);
    }
};

// 방 ID를 서버에 저장하는 함수
const saveRoomIdToServer = async (roomId) => {
    try {
        await fetch('/videoConference/rooms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ roomId })  // 수정된 부분
        });
    } catch (error) {
        console.error("Failed to save room ID to server:", error);
    }
};

// 방 ID를 서버에서 확인하는 함수
const checkRoomIdFromServer = async (roomId) => {
    try {
        // 서버에서 방 ID 확인하는 API 호출
        const response = await fetch(`/videoConference/rooms/${roomId}`);
        if (response.ok) {
            const data = await response.json();
            return data;  // 서버에서 받은 boolean 값을 반환
        } else {
            return false;
        }
    } catch (error) {
        console.error("Failed to check room ID from server:", error);
        return false;
    }
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

const toggleStartEndButton = (buttonSelector, endText, endHandler) => {
    const button = document.querySelector(buttonSelector);
    button.textContent = endText;
    button.removeEventListener('click', startConferenceHandler); // startConferenceHandler를 정의했기 때문에 사용
    button.addEventListener('click', endHandler);
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

// UUID 생성 함수
function generateUUID() {
    return 'xxxx-xxxx-xxxx-xxxx'.replace(/[xy]/g, function(c) {
        const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

// 페이지 로드 시 createRoomIdInput에 UUID 설정
document.addEventListener('DOMContentLoaded', async (event) => {
    await syncRoomIdsWithServer(); // 방 ID를 서버와 동기화
    const newRoomId = generateUUID(); // 새로운 UUID 생성
    document.querySelector('#createRoomIdInput').value = newRoomId; // 생성된 UUID를 input 박스에 설정
    existingRoomIds.push(newRoomId); // 새로 생성된 방 ID를 배열에 추가
});

// 방만들기 버튼 이벤트 리스너
document.querySelector('#createEnterRoomBtn').addEventListener('click', async () => {
    await startCam();
    roomId = document.querySelector('#createRoomIdInput').value;
    console.log(`Entered room: ${roomId}`);
    document.querySelector('#createRoomIdInput').disabled = true;
    document.querySelector('#createEnterRoomBtn').disabled = true;
    document.querySelector('#joinRoomIdInput').disabled = true;
    document.querySelector('#joinEnterRoomBtn').disabled = true;
    await connectSocket();
    document.querySelector('#createStartStreamsBtn').style.display = 'block';
    await saveRoomIdToServer(roomId); // 방 ID를 서버에 저장
});

// 참여하기 버튼 이벤트 리스너
document.querySelector('#joinEnterRoomBtn').addEventListener('click', async () => {
    await startCam();
    roomId = document.querySelector('#joinRoomIdInput').value;
    console.log(`Entered room: ${roomId}`);
    document.querySelector('#joinRoomIdInput').disabled = true;
    document.querySelector('#joinEnterRoomBtn').disabled = true;
    document.querySelector('#createRoomIdInput').disabled = true;
    document.querySelector('#createEnterRoomBtn').disabled = true;
    await connectSocket();
    document.querySelector('#joinStartStreamsBtn').style.display = 'block';
});

// 방 만들기 후 시작하기 버튼 이벤트 리스너
document.querySelector('#createStartStreamsBtn').addEventListener('click', async () => {
    console.log('Start button clicked');
    await startConferenceHandler(); // 방 시작하기 핸들러 호출
    document.querySelector('#createStartStreamsBtn').style.display = 'none'; // 시작하기 버튼 숨기기
    // 종료하기 및 카메라끄기 버튼 표시
    document.getElementById('controlButtons').style.display = 'block';
});

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

// 참여하기 후 시작하기 버튼 이벤트 리스너
document.querySelector('#joinStartStreamsBtn').addEventListener('click', async () => {
    roomId = document.querySelector('#joinRoomIdInput').value;
    const roomExists = await checkRoomIdFromServer(roomId); // 서버에서 방 ID 확인
    if (!roomExists) {
        alert('채팅방 ID를 확인해주세요.');
        // 컴포넌트 다시 활성화
        document.querySelector('#joinRoomIdInput').disabled = false;
        document.querySelector('#joinEnterRoomBtn').disabled = false;
        document.querySelector('#createRoomIdInput').disabled = false;
        document.querySelector('#createEnterRoomBtn').disabled = false;

        // 시작하기 버튼과 카메라 화면 숨기기
        document.querySelector('#joinStartStreamsBtn').style.display = 'none';
        localStreamElement.style.display = 'none';
        localStreamElement.srcObject = null;

        return;
    }
    document.querySelector('#joinStartStreamsBtn').style.display = 'none'; // 시작하기 버튼 숨기기
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

    // 종료하기 및 카메라끄기 버튼 표시
    document.getElementById('controlButtons').style.display = 'block';
});

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