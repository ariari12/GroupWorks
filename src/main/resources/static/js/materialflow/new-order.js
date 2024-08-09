let orderCode;

document.addEventListener("DOMContentLoaded", function () {
    orderCode = document.getElementById("orderCode").value;
    disableEnterSubmit();
    createOrderCode();

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
        document.getElementById('totalTex').value = Math.floor(total / (100 / tex));
    }
}

function frmSubmit(event) {
    event.preventDefault(); // form 제출 막기

    // 폼 데이터 수집
    const orderCode = document.getElementById("orderCode").value;
    const itemCode = orderCode.substring(11, orderCode.length);
    const bomList = [];
    let i = 1;

    // 각 품목 행의 데이터를 수집하여 배열에 추가
    document.querySelectorAll('#itemTable tbody tr').forEach(function (row) {
        bomList.push({
            id: 0,
            itemCode: itemCode + "-" + i,
            itemName: row.querySelector('input[name="itemName"]').value,
            quantity: row.querySelector('input[name="itemQuantity"]').value,
            unitPrice: row.querySelector('input[name="itemPrice"]').value,
            itemTotal: row.querySelector('input[name="itemTotal"]').value
        });
        i++;
    });

    if(bomList.length < 1) {
        alert("품목은 1종류 이상 있어야 합니다.");
        return;
    }

    // 발주서 데이터 객체 생성
    const orderData = {
        id: 0,
        orderCode: orderCode,
        classification: document.getElementById('division').value,
        totalAmount: document.getElementById('total').value,
        tex: document.getElementById('tex').value,
        orderDate: document.getElementById('orderDate').value.replaceAll(/-/g, "."),
        dueDate: document.getElementById('dueDate').value.replaceAll(/-/g, "."),

        address: document.getElementById('address').value,
        addressDetail: document.getElementById('addressDetail').value,
        zipCode: document.getElementById('zipCode').value,

        employee: { id: document.getElementById('employeeId').value },
        manager: { id: document.getElementById('managerId').value },
        bomList: bomList,
        mesList: null
    };

    // 모달 창 띄우기
    const modal = new bootstrap.Modal(document.getElementById('modal'));
    modal.show();
    // 모달 닫기 (닫기 버튼 클릭 시)
    // document.querySelector(".close-btn").addEventListener("click", e => modal.hide());
    // 작성 취소 버튼 클릭 시
    document.getElementById("cns-Btn").addEventListener("click", e => modal.hide());
    // 작성 등록 버튼 클릭 시
    document.getElementById('okBtn').addEventListener('click', function (e) {
        const t = event.target;
        ajaxRequest(t.action, t.method, orderData, function (c) {
            console.log(c);
            if(c.result) {
                alert(c.message);
                location.href = "/materialflow/order-record";
            } else {
                modal.hide();
                alert(c.message);
            }
        }, function (e) {
            modal.hide();
            alert(e.responseText)
        })
    })
}

function ajaxRequest(url, method, data, callback, err) {
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (r) {
            callback(r);
        }, error: function (x, e, r) {
            console.error(x);
            console.error(e);
            console.error(r);
            err(x);
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