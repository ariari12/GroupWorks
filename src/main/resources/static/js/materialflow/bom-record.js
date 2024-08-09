const WINDOW_SIZE = "width=1400, height=1200, left=300, top=30";
let currentWindow = null; // 공용 창 변수
let currentListener = null; // 공용 리스너 변수
let preUrl = null; // 이전 Window url

document.addEventListener('DOMContentLoaded', function() {
    tableSet();
});

function tableSet() {
    $('#table').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 20, 30, 40, 50, 75, 100 ],
        displayLength: 30,
    });
}

function load(pk, code, name) {
    const url = "/materialflow/item/" + pk + "/" + code + "/" + name;
    openWindow(url, "BOM 자재 현황", WINDOW_SIZE);
}

/* window 기존 창을 닫고 새창을 열고 Listener 등록 함수 */
function openWindow(url, windowName, windowSize) {
    if(preUrl === url && !currentWindow.closed) {
        currentWindow.focus();
        return;
    }
    // 다른 새창이 열릴 경우 기존 창 닫기
    if(currentWindow) {
        currentWindow.close();
        preUrl = null;
    }
    // 새 창 열기
    currentWindow = window.open(url, windowName, windowSize);
}
