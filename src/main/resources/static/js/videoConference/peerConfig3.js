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

// 참여하기 버튼 이벤트 리스너
document.querySelector('#joinEnterRoomBtn').addEventListener('click', async () => {
    roomId = document.querySelector('#joinRoomIdInput').value;

    const roomExists = await checkRoomIdFromServer(roomId); // 서버에서 방 ID 확인
    if (!roomExists) {
        alert('채팅방 ID를 확인해주세요.');
        // 컴포넌트 다시 활성화
        document.querySelector('#joinRoomIdInput').disabled = false;
        document.querySelector('#joinEnterRoomBtn').disabled = false;

        // 시작하기 버튼 숨기기
        document.querySelector('#joinStartStreamsBtn').style.display = 'none';


    }else{
        console.log(`Entered room: ${roomId}`);
        document.querySelector('#joinStartStreamsBtn').style.display = 'block';
        document.querySelector('#joinRoomIdInput').disabled = true;
        document.querySelector('#joinEnterRoomBtn').disabled = true;
    }

});