document.addEventListener("DOMContentLoaded", function () {
    disableEnterSubmit();

    document.getElementById('registerBusiness').addEventListener("click", ev => registerWindow());
    document.getElementById('chooseBusiness').addEventListener("click", ev => chooseWindow());
    document.getElementById('orderForm').addEventListener('submit', ev => frmSubmit(ev));

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

/* 수주/발주 번호 정하기 */
function documentNumbDefined() {
    $("#docsNum").val(userNum + deptNum + " - ");
    for (const item of tabNames) {
        $(item).mouseout(function () {
            let suffix = $("#category option:selected").val() + " - ";
            suffix += $("#draft-date").val().replaceAll("-","").replace("T","").replace(":","");
            $("#docsNum").val(userNum + deptNum + " - " + suffix);
        });
    }
}

/* 엔터 키 전송 막기 */
function disableEnterSubmit() {
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
    cell3.innerHTML = '<input type="number" name="itemPrice" class="table-input" placeholder="단가를 입력하세요" min="0" step="0.01" required oninput="calculateTotal(this)">';
    cell4.innerHTML = '<div class="currency-wrapper"><input type="number" name="itemTotal" class="table-input" placeholder="총액을 입력하세요" readonly><span class="currency">￦</span></div>';
    cell5.className = 'delete-column';

    // 삭제 버튼을 추가
    const deleteButton = document.createElement('button');
    deleteButton.className = 'btn btn-danger';
    deleteButton.onclick = function() { removeItem(this); };
    deleteButton.innerHTML = '<i class="fas fa-trash-alt"></i>';
    cell5.appendChild(deleteButton);

    // 새로 추가된 행으로 스크롤
    newRow.scrollIntoView({ behavior: 'smooth', block: 'center' });
}

// 품목 삭제 함수
function removeItem(button) {
    const row = button.closest('tr');
    row.remove();
}


/* 수량과 단가 입력값이 변경될 때 총액 계산 */
function calculateTotal(input) {
    const row = input.closest('tr');
    const quantity = row.querySelector('input[name="itemQuantity"]').value;
    const price = row.querySelector('input[name="itemPrice"]').value;
    const totalField = row.querySelector('input[name="itemTotal"]');

    // 총액 계산
    const total = quantity * price;
    totalField.value = total; // 소수점 두 자리까지 표시
}

function frmSubmit(event) {
    event.preventDefault(); // form 제출 마기

    // 폼 데이터 수집
    const orderNumber = document.getElementById('orderNumber').value;
    const orderDate = document.getElementById('orderDate').value;
    const supplier = document.getElementById('supplier').value;
    const items = [];

    // 각 품목 행의 데이터를 수집하여 배열에 추가
    document.querySelectorAll('#itemTable tbody tr').forEach(function (row) {
        const itemName = row.querySelector('input[name="itemName"]').value;
        const itemQuantity = row.querySelector('input[name="itemQuantity"]').value;
        const itemPrice = row.querySelector('input[name="itemPrice"]').value;
        const itemTotal = row.querySelector('input[name="itemTotal"]').value;
        items.push({itemName, itemQuantity, itemPrice, itemTotal});
    });

    // 발주서 데이터 객체 생성
    const orderData = {
        orderNumber,
        orderDate,
        supplier,
        items
    };

    // 콘솔에 데이터 출력 (실제 구현에서는 AJAX 등을 사용해 서버로 전송)
    // ajaxRequest('', )
    console.log('Order Data:', orderData);
}

/* Ajax */
function ajaxRequest(url, method, data, callback) {
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
        }
    })
}