document.addEventListener("DOMContentLoaded", function () {
    disableEnterSubmit();
})

/* 엔터 키 전송 막기 */
function disableEnterSubmit() {
    $('.prevent-enter-submit').on('keydown', function(event) {
        if (event.key === 'Enter') { event.preventDefault(); }
    });
}

/* 등록 취소 */
function canselOrder(pk, c) {
    $.ajax({
        url: "/materialflow/order-detail/" + pk,
        type: "delete",
        success: (r) => {
            console.log(r);
            if(r.result) {
                alert(r.message);
                location.href = "/materialflow/order-record";
            } else {
                alert(r.message);
                location.href = "/materialflow/order-detail/" + pk + "/" + c;
            }
        },
        error: (x, r, d) => {
            console.error(x);
            console.error(r);
            console.error(d);
        }
    })
}

/* 인쇄 */
let printWindow;
let prtContent;
let originalBodyContent;
function printBtn () {
    // 프린트할 영역의 id 값을 통해 콘텐츠를 가져옵니다.
    prtContent = document.getElementById("orderForm").innerHTML;
    let title = document.getElementById("title").innerHTML;
    // 기존 페이지의 내용을 숨기기 위한 스타일을 정의합니다.
    originalBodyContent = document.body.innerHTML;

    // 인쇄할 콘텐츠를 새로운 div에 추가합니다.
    printWindow = window.open('', '', 'width=1500,height=2000');
    printWindow.document.open();
    printWindow.document.write(`
            <html>
                <head>
                    <title>${title}</title>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
                    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
                    <link href="/css/styles.css" rel="stylesheet" />
                    <link rel="stylesheet" href="/css/materialflow/order-detail.css">
                </head>
                <body>
                    <div id="orderForm">
                        ${prtContent}
                    </div>
                </body>
            </html>
        `);
    printWindow.document.close();

    printWindow.onload = function() {
        printWindow.print();
        // 인쇄 대화 상자가 닫힐 때 창을 닫습니다.
        setTimeout(() => printWindow.close(), 100);
    };
}

function toastInfo(msgTag) {
    // 템플릿 토스트 요소를 복제하여 새로운 토스트 생성
    const clonedToast = document.getElementById('managerToastTemplate').cloneNode(true);

    // 토스트 내용 업데이트
    clonedToast.querySelector('.toast-body').innerHTML = msgTag;

    // 복제된 토스트를 토스트 컨테이너에 추가
    document.querySelector('.toast-container').appendChild(clonedToast);

    // Initialize Bootstrap Toast for the new toast
    const newToast = new bootstrap.Toast(clonedToast);
    newToast.show();
}