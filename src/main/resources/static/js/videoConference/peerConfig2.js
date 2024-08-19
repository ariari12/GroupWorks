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
    roomId = document.querySelector('#createRoomIdInput').value;
    console.log(`Entered room: ${roomId}`);
    document.querySelector('#createStartStreamsBtn').style.display = 'block';
    document.querySelector('#createRoomIdInput').disabled = true;
    document.querySelector('#createEnterRoomBtn').disabled = true;

    await saveRoomIdToServer(roomId); // 방 ID를 서버에 저장
});



