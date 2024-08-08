const WINDOW_SIZE = "width=1400, height=1200, left=300, top=30";
let currentWindow = null; // 공용 창 변수
let currentListener = null; // 공용 리스너 변수
let preUrl = null; // 현재 열려있는 창에 URL

document.addEventListener('DOMContentLoaded', function (e) {
    
})

function load(pk) {
    const url = "/materialflow/item/" + pk;
    openWindow(url, "BOM 자재 현황", WINDOW_SIZE);
}

/* window 기존 창을 닫고 새창을 열고 Listener 등록 함수 */
function openWindow(url, windowName, windowSize, eventHandler, message) {
    // 기존 창이 열려 있다면 포커스
    if (preUrl === url && currentWindow) {
        currentWindow.focus();
        return;
    }
    // 다른 새창이 열릴 경우 기존 창 닫기
    if(currentWindow) currentWindow.close();

    // 새 창 열기
    currentWindow = window.open(url, windowName, windowSize);
    preUrl = url;

    if (eventHandler) {
        // 창이 열리고 나서 이벤트 리스너 추가
        currentListener = eventHandler;
        window.addEventListener("message", currentListener, false);

        // 창이 로드된 후 메시지 전송
        if (message) {
            currentWindow.onload = () => {
                if (currentWindow && currentWindow.opener) {
                    message(); // 메시지를 전송하는 함수 호출
                } else {
                    console.error("No opener found for the current window.");
                }
            };
        }
    }
}
function windowClosed() {
    currentWindow.addEventListener('unload', function (e) {
        currentWindow.removeEventListener("message", currentListener);
        currentWindow = null; // 윈도우 객체 초기화
        currentListener = null; // 리스너 초기화
        preUrl = null; // URL 초기화
    })
}

