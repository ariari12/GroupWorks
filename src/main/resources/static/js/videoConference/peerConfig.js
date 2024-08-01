let localStreamElement = document.querySelector('#localStream');
const myKey = Math.random().toString(36).substring(2, 11);
let pcListMap = new Map();
let roomId;
let otherKeyList = [];
let localStream = undefined;
let stompClient;
const maxRetries = 5;
let retryCount = 0;

// 모달 관련 요소들
const createModal = document.getElementById("createModal");
const joinModal = document.getElementById("joinModal");
const span = document.getElementsByClassName("close");

// Room ID 표시 요소
const roomIdDisplay = document.getElementById("roomIdDisplay");

// 랜덤 UUID 생성 함수
const generateUUID = () => {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        let r = Math.random() * 16 | 0,
            v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
};

// 모달 닫기
Array.from(span).forEach(element => {
    element.onclick = () => {
        createModal.style.display = "none";
        joinModal.style.display = "none";
    };
});

// 모달 외부 클릭 시 닫기
window.onclick = (event) => {
    if (event.target === createModal) {
        createModal.style.display = "none";
    }
    if (event.target === joinModal) {
        joinModal.style.display = "none";
    }
};

document.querySelector('#createRoomBtn').addEventListener('click', () => {
    roomId = generateUUID();
    roomIdDisplay.innerText = `Room ID: ${roomId}`;
    createModal.style.display = "block";
});

document.querySelector('#startMeetingBtn').addEventListener('click', async () => {
    createModal.style.display = "none";
    await startCam();
    await connectSocket();
});

document.querySelector('#joinRoomBtn').addEventListener('click', () => {
    joinModal.style.display = "block";
});

document.querySelector('#joinRoomConfirmBtn').addEventListener('click', async () => {
    roomId = document.querySelector('#roomIdInput').value;
    if (roomId) {
        console.log(`Joined room with ID: ${roomId}`);
        joinModal.style.display = "none";
        await startCam();
        await connectSocket();
    }
});

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

const handleMediaError = async (error) => {
    console.error("Media error: ", error);
    displayBlackScreen();
};

const displayBlackScreen = () => {
    localStreamElement.classList.add('black-screen');
    localStreamElement.style.display = 'block';
};

const connectSocket = async () => {
    const socket = new SockJS('/signaling');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;

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
        }
    };

    stompClient.connect({}, onConnect, onError);
};

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
};

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
        document.getElementById('remoteStreamDiv').appendChild(video);
    }
};

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

let onIceCandidate = (event, otherKey) => {
    if (event.candidate) {
        console.log('Sending ICE candidate:', event.candidate);
        stompClient.send(`/app/peer/iceCandidate/${otherKey}/${roomId}`, {}, JSON.stringify({
            key: myKey,
            body: event.candidate
        }));
    }
};

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

const setLocalAndSendMessage = (pc, sessionDescription) => {
    pc.setLocalDescription(sessionDescription).then(() => {
        console.log('Set local description:', sessionDescription);
    }).catch(error => {
        console.error('Error setting local description:', error);
    });
};
