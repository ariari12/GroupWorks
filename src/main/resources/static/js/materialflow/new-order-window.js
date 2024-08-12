let currentWindow = null; // 공용 창 변수
let currentListener = null; // 공용 리스너 변수
let preUrl = null; // 현재 열려있는 창에 URL

const WINDOW_SIZE = "width=1400, height=1200, left=300, top=30";

document.addEventListener("DOMContentLoaded", function () {
    registerWindow();
    chooseBusinessWindow();
    chooseEmployeeWindow();
    chooseManagerWindow();
})

/* window 기존 창을 닫고 새창을 열고 Listener 등록 함수 */
function openWindow(url, windowName, windowSize, eventHandler) {
    // 기존 창(이전 url 과 새로여는 url 이 같은 경우)이 열려 있다면 포커스
    if (preUrl === url && !currentWindow.closed) {
        currentWindow.focus();
        return;
    }
    if(currentWindow != null && currentWindow.closed) preUrl = null; // 창닫힘 기존
    if (eventHandler) {
        // 다른 새창이 열릴 경우 기존 창 닫기
        if(currentWindow) currentWindow.close();

        // 새 창 열기
        currentWindow = window.open(url, windowName, windowSize);
        preUrl = url;

        // 창이 열리고 나서 이벤트 리스너 추가
        currentListener = eventHandler;
        window.addEventListener("message", currentListener, { once : true });
    }
}

/* 거래처 등록 */
function registerWindow() {
    document.getElementById('registerBusiness').addEventListener("click", ev => {
        openWindow("/materialflow/new-business", "거래처 등록", "width=805, height=510, left=470, top=150",
            ev => businessInfoUpdate(ev))
    });
}
/* 거래처 목록 */
function chooseBusinessWindow() {
    document.getElementById('chooseBusiness').addEventListener("click", ev => {
        openWindow("/materialflow/business-select", "거래처 목록", WINDOW_SIZE, ev => businessInfoUpdate(ev))
    });
}
/* 거래처 정보 업데이트, 거래처 담당자 정보 초기화 */
function businessInfoUpdate(event) {
    if (event.origin !== window.location.origin) return;
    if(event.data.id === undefined) return;

    const division = document.getElementById("division").value;
    if(division === '1') {
        document.getElementById("businessId").value = event.data.id;
        document.getElementById("receiveBusiness").value = event.data.businessName;
    } else if(division === '2') {
        document.getElementById("businessId").value = event.data.id;
        document.getElementById("businessNumber").value = event.data.businessNumber;
        document.getElementById("businessName").value = event.data.businessName;
        document.getElementById("type").value = event.data.type;
        document.getElementById("item").value = event.data.item;
    }

    document.getElementById("managerId").value = null;
    document.getElementById("managerName").value = null;
    document.getElementById("managerPhone").value = null;
    document.getElementById("managerEmail").value = null;
}

/* 거래처 담당자 선택 */
function chooseManagerWindow() {
    document.getElementById('chooseManager').addEventListener("click", ev => {
        let businessId = document.getElementById("businessId").value;
        if(businessId === undefined || businessId < 1) {
            toastInfo("<span style=\"color: #bb2d3b; font-size: 16px;\">거래처를 먼저 선택해주세요.</span>");
            return;
        }
        openWindow("/materialflow/manager-select/" + businessId, "거래처 목록", WINDOW_SIZE,
                ev => managerReceive(ev)
        );
    });
}
/* 거래처 담당자 정보 받기 */
function managerReceive(event) {
    if (event.origin !== window.location.origin) { return; }
    if(currentWindow.closed) preUrl = null; // 창닫힘
    if(event.data.id === undefined) return;

    document.getElementById("managerId").value = event.data.id;
    document.getElementById("managerName").value = event.data.name;
    document.getElementById("managerPhone").value = event.data.phone;
    document.getElementById("managerEmail").value = event.data.email;

}

/* 담당자 사원 선택 */
function chooseEmployeeWindow() {
    document.getElementById('chooseEmployee').addEventListener("click", ev => {
        openWindow("/materialflow/employee-select", "사원 목록", WINDOW_SIZE, ev => employeeReceive(ev))
    });
}
/* 담당자 사원 정보 받기 */
function employeeReceive(event) {
    if (event.origin !== window.location.origin) { return; }
    if(currentWindow.closed) preUrl = null; // 창닫힘
    if(event.data.id === undefined) return;

    document.getElementById("employeeId").value = event.data.id;
    document.getElementById("employeeName").value = event.data.name;
    document.getElementById("employeePhone").value = event.data.phone;
    document.getElementById("employeeEmail").value = event.data.email;
}
