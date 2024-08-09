let orderCode;
document.addEventListener("DOMContentLoaded", function () {
    orderCode = document.getElementById("orderCode").value;
    disableEnterSubmit();
    createOrderCode();

    document.getElementById('registerBusiness').addEventListener("click", ev => registerWindow());
    document.getElementById('chooseBusiness').addEventListener("click", ev => chooseBusinessWindow());
    document.getElementById('chooseEmployee').addEventListener("click", ev => chooseEmployeeWindow());
    document.getElementById('chooseManager').addEventListener("click", ev => chooseManagerWindow());
    document.getElementById('findZip').addEventListener("click", ev => addresZip());

    document.getElementById('orderForm').addEventListener('submit', ev => frmSubmit(ev));
})

/* 엔터 키 전송 막기 */
function disableEnterSubmit() {
    $('.prevent-enter-submit').on('keydown', function(event) {
        if (event.key === 'Enter') { event.preventDefault(); }
    });
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
                    <link rel="stylesheet" href="/css/materialflow/new-order.css">
                </head>
                <body>
                    <form>
                        ${prtContent}
                    </form>
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

/* 거래처 등록 */
let registerBusinessWindow;
function registerWindow() {
    if (!registerBusinessWindow || registerBusinessWindow.closed) {
        registerBusinessWindow = window.open("/materialflow/new-business", "거래처 등록",  "width=805, height=510, left=470, top=150");
        window.addEventListener("message", ev => businessReceive(ev), false);
    } else {
        registerBusinessWindow.focus();
    }
}

const WINDOW_SIZE = "width=1400, height=1200, left=300, top=30";

/* 거래처 목록 */
let businessListWindow;
let businessListener;
function chooseBusinessWindow() {
    if (!businessListWindow || businessListWindow.closed) {
        businessListWindow = window.open("/materialflow/business-select", "거래처 목록",  WINDOW_SIZE);
        businessListener = ev => businessReceive(ev);
        window.addEventListener("message", businessListener, false);
    } else {
        businessListWindow.focus();
    }
}
/* 거래처 정보 받기 */
function businessReceive(event) {
    if (event.origin !== window.location.origin) {
        return;
    }
    const division = document.getElementById("division").value;
    if(division === '1') {
        document.getElementById("businessId").value = event.data.id;
        document.getElementById("receiveBusiness").value = event.data.businessName;
    } else if(division === '2') {
        document.getElementById("businessId").value = event.data.id;
        document.getElementById("businessNumber").value = event.data.businessNumber;
        document.getElementById("orderBusiness").value = event.data.businessName;
        document.getElementById("type").value = event.data.type;
        document.getElementById("item").value = event.data.item;
    }
    window.removeEventListener("message", businessListener);
}

/* 거래처 담당자 선택 */
let managerListWindow;
let managerListener;
function chooseManagerWindow() {
    if (!managerListWindow || managerListWindow.closed) {
        let businessId = document.getElementById("businessId").value;
        console.log(businessId);
        if(businessId === undefined || businessId < 1) {
            toastInfo("<span style=\"color: #bb2d3b; font-size: 16px;\">거래처를 먼저 선택해주세요.</span>");
            return;
        }
        managerListWindow = window.open("/materialflow/manager-select/" + businessId, "거래처 목록",  WINDOW_SIZE);
        managerListener = ev => managerReceive(ev);
        window.addEventListener("message", managerListener, false);
    } else {
        managerListWindow.focus();
    }
}
/* 담당자 사원 정보 받기 */
function managerReceive(event) {
    if (event.origin !== window.location.origin) { return; }

    const division = document.getElementById("division").value;
    document.getElementById("managerId").value = event.data.id;

    document.getElementById("managerName").value = event.data.name;
    document.getElementById("managerPhone").value = event.data.phone;
    document.getElementById("managerEmail").value = event.data.email;
    window.removeEventListener("message", managerListener);
}


/* 담당자 사원 선택 */
let employeeListWindow;
let employeeListener;
function chooseEmployeeWindow() {
    if (!employeeListWindow || employeeListWindow.closed) {
        employeeListWindow = window.open("/materialflow/employee-select", "거래처 목록",  WINDOW_SIZE);
        employeeListener = ev => employeeReceive(ev);
        window.addEventListener("message", employeeListener, false);
    } else {
        employeeListWindow.focus();
    }
}
/* 담당자 사원 정보 받기 */
function employeeReceive(event) {
    if (event.origin !== window.location.origin) { return; }

    const division = document.getElementById("division").value;
    document.getElementById("employeeId").value = event.data.id;

    document.getElementById("employeeName").value = event.data.name;
    document.getElementById("employeePhone").value = event.data.phone;
    document.getElementById("employeeEmail").value = event.data.email;
    window.removeEventListener("message", employeeListener);
}

/* 납품 주소, 우편번호 */
function addresZip() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우 참고항목을 조합한다.
                addr = data.roadAddress;
                var addrRef = '';
                // 법정동명이 있을 경우 추가한다. (법정리는 제외) 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    addrRef += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    addrRef += (addr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(addrRef !== '') {
                    addr += ' (' + addrRef + ')';
                }
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            document.getElementById("address").value = addr;
            document.getElementById("zipCode").value = data.zonecode;
            document.getElementById("addressDetail").focus();
        }
    }).open();
}

/* 수주/발주 번호 정하기 */
function createOrderCode() {
    let rowCnt = document.querySelectorAll('#itemTable tbody>tr').length;
    document.getElementById("orderCode").value = orderCode + rowCnt;
}
/* 품목 추가 함수 */
function addItem() {
    const itemTable = document.getElementById('itemTable').getElementsByTagName('tbody')[0];
    const newRow = itemTable.insertRow();

    const cell1 = newRow.insertCell(0);
    const cell2 = newRow.insertCell(1);
    const cell3 = newRow.insertCell(2);
    const cell4 = newRow.insertCell(3);
    const cell5 = newRow.insertCell(4);

    cell1.innerHTML = '<input type="text" name="itemName" class="table-input" placeholder="품목명을 입력하세요" required>';
    cell2.innerHTML = '<input type="number" name="itemQuantity" class="table-input" placeholder="수량을 입력하세요" min="0" step="1" required oninput="calculateTotal(this)">';
    cell3.innerHTML = '<input type="number" name="itemPrice" class="table-input" placeholder="단가를 입력하세요" min="0" step="10" required oninput="calculateTotal(this)">';
    cell4.innerHTML = '<div class="currency-wrapper"><input type="number" name="itemTotal" class="table-input" placeholder="총액을 입력하세요" readonly><span class="currency">￦</span></div>';
    cell5.className = 'delete-column';

    const deleteButton = document.createElement('button');
    deleteButton.className = 'btn btn-danger none-print';
    deleteButton.onclick = function() { removeItem(this); };
    deleteButton.innerHTML = '<i class="fas fa-trash-alt"></i>';
    cell5.appendChild(deleteButton);
    // 수주/발주 번호 업데이트
    createOrderCode();

    newRow.scrollIntoView({ behavior: 'smooth', block: 'center' });
}
/* 품목 삭제 함수 */
function removeItem(button) {
    const row = button.closest('tr');
    row.remove();

    // 수주/발주 번호 업데이트
    createOrderCode();
}

/* 수량과 단가 입력값이 변경될 때 총액 계산 */
function calculateTotal(input) {
    const row = input.closest('tr');
    let quantity = row.querySelector('input[name="itemQuantity"]').value;
    if(quantity < 1) {
        row.querySelector('input[name="itemQuantity"]').value = 1;
        quantity = 1;
    }
    let price = row.querySelector('input[name="itemPrice"]').value;
    if(price < 10) {
        row.querySelector('input[name="itemPrice"]').value = 10;
        price = 10;
    }
    const totalField = row.querySelector('input[name="itemTotal"]');
    const total = quantity * price;
    totalField.value = total;
    updateTotal();
}
/* 모든 품목 합계 계산 */
function updateTotal() {
    const rows = document.querySelectorAll('#itemTable tbody tr');
    let total = 0;

    rows.forEach(row => {
        const itemTotal = row.querySelector('[name="itemTotal"]').value;
        total += parseFloat(itemTotal) || 0;
    });
    document.getElementById('total').value = total;
    let tex = document.getElementById('tex').value;
    if(tex > 1) {
        document.getElementById('totalTex').value = total / (100 / tex);
    }
}

/* form 제출 */
function frmSubmit(event) {
    event.preventDefault(); // form 제출 막기

    // 폼 데이터 수집
    const items = [];

    // 각 품목 행의 데이터를 수집하여 배열에 추가
    document.querySelectorAll('#itemTable tbody tr').forEach(function (row) {
        items.push({
            itemName: row.querySelector('input[name="itemName"]').value,
            itemQuantity: row.querySelector('input[name="itemQuantity"]').value,
            itemPrice: row.querySelector('input[name="itemPrice"]').value,
            itemTotal: row.querySelector('input[name="itemTotal"]').value
        });
    });

    // 발주서 데이터 객체 생성
    const orderData = {
        orderCode: document.getElementById('orderCode').value,
        classification: document.getElementById('division').value,
        totalAmount: document.getElementById('total').value,
        tex: document.getElementById('tex').value,
        orderDate: document.getElementById('orderDate').value,
        dueDate: document.getElementById('dueDate').value,
        items
    };

    // 콘솔에 데이터 출력 (실제 구현에서는 AJAX 등을 사용해 서버로 전송)
    // ajaxRequest('', )
    console.log('Order Data:', orderData);
    console.log("제출");
}

/* Ajax */
function ajaxRequest(url, method, data, callback, err) {
    $.ajax({
        url: url,
        type: method,
        data: data,
        success: function (r) {
            callback(r);
        }, error: function (x, e, r) {
            console.error(x);
            console.error(e);
            console.error(r);
            err();
        }
    })
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