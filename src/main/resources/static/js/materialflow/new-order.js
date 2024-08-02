document.addEventListener("DOMContentLoaded", function () {
    documentNumbDefined();
    disableEnterSubmit();

    document.getElementById('registerBusiness').addEventListener("click", ev => registerWindow());
    document.getElementById('chooseBusiness').addEventListener("click", ev => chooseWindow());

    // 부모 창에서 메시지를 수신하는 이벤트 리스너
    window.addEventListener("message", ev => businessReceive(ev), false);
})

/* 거래처 등록 */
let rWindow;
function registerWindow() {
    if (!rWindow || rWindow.closed) {
        rWindow = window.open("/materialflow/new-business", "거래처 등록",  "width=805, height=510, left=470, top=150");
    } else {
        // 새 창이 이미 열려 있는 경우에는 포커스를 맞춥니다.
        rWindow.focus();
    }
}

function documentNumbDefined() {
    /* 문서 번호 정하기 */
    $("#docsNum").val(userNum + deptNum + " - ");
    for (const item of tabNames) {
        $(item).mouseout(function () {
            let suffix = $("#category option:selected").val() + " - ";
            suffix += $("#draft-date").val().replaceAll("-","").replace("T","").replace(":","");
            $("#docsNum").val(userNum + deptNum + " - " + suffix);
        });
    }
}

function disableEnterSubmit() {
    /* 엔터 키 전송 막기 */
    $('.prevent-enter-submit').on('keydown', function(event) {
        if (event.key === 'Enter') { event.preventDefault(); }
    });
}

/* 거래처 목록 */
let cWindow;
function chooseWindow() {
    if (!cWindow || cWindow.closed) {
        cWindow = window.open("/materialflow/business-select", "거래처 목록",  "width=1400, height=1000, left=300, top=30");
    } else {
        // 새 창이 이미 열려 있는 경우에는 포커스를 맞춥니다.
        cWindow.focus();
    }
}

/* 거래처 정보 받기 */
function businessReceive(event) {
    // 보낸 메시지가 신뢰할 수 있는 출처에서 온 것인지 확인 (origin 확인)
    if (event.origin !== window.location.origin) {
        return;
    }
    // HTML 컨텐츠를 받기
    console.log(event.data);
}
