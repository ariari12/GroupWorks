document.addEventListener('DOMContentLoaded', function (e) {
    tableSet();
    materialStatusUpdate();
    itemCodeTableSet();
    qrPrint();
    completeCheck();
});

function tableSet() {
    $('#table').DataTable({
        info: false,
        ordering: true,
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 500,
        lengthMenu: [ 20, 30, 40, 50, 75, 100 ],
        displayLength: 30,
    });
}

function itemCodeTableSet() {
    $('#itemCodeTable').DataTable({
        info: false,
        ordering: true,
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 300,
        lengthMenu: [ 5, 10, 15, 20, 25, 30 ],
        displayLength: 5,
    });
}

function materialStatusUpdate() {
    document.getElementById('statusUpdateFrm').addEventListener('submit', ev => {
        ev.preventDefault();

        const modal = new bootstrap.Modal(document.getElementById('modal'));
        if(itemCodeList.length < 1) {
            modal.hide();
            alert("최소 1개 이상의 자재를 선택해야 합니다.");
            return;
        }

        const data = {
            itemCodeList: itemCodeList,
            item: {
                "itemStatus": parseInt(document.querySelector('#statusUpdateFrm input[name="itemStatus"]:checked').value),
                "storageManager": document.getElementById("storageManager").value,
                "storageLocation": document.getElementById("storageLocation").value,
                "deliveryManager": document.getElementById("deliveryManager").value,
                "deliveryLocation": document.getElementById("deliveryLocation").value,
            }
        };

        ajaxRequest("/materialflow/material", "put", data, function (r) {
            alert(r.message); modal.hide(); location.href = window.location.pathname; },
            (e) => modal.hide()
        );
    });
}

let itemCodeList = [];
function onCheckboxChange(checkbox, itemCode) {
    if (checkbox.checked) {
        itemCodeList.push(itemCode);
        document.getElementById("checkList").innerHTML += '<li class="dropdown-item">' + itemCode + '</li>';
    } else {
        const index = itemCodeList.indexOf(itemCode);
        if (index !== -1) {
            itemCodeList.splice(index, 1);
            document.querySelectorAll('.dropdown-item').forEach(function(item) {
                if (item.innerText === itemCode) { item.remove(); }
            });
        }
    }
}

function qrPrint() {
    document.getElementById("qrBtn").addEventListener('click', function (e) {
        const itemCode = document.getElementById("itemCode").value;
        const bomId = document.getElementById("bomId").value;
        window.open("/materialflow/qr/qr-code/" + bomId, itemCode + " 자재 QR CODE", "width=1000, height=2000, left=500, top=0")
    })
}

function completeCheck() {
    document.getElementById("sendCompleteBtn").addEventListener('click', () =>
        sendCompleteCheck(1, (r) => !r ? alert("발주완료는 품목 내 자재가 모두 출고되어야 합니다.") : null));
    document.getElementById("receiveCompleteBtn").addEventListener('click', () =>
        sendCompleteCheck(2, (r) => !r ? alert("수주완료는 품목 내 자재가 모두 입고되어야 합니다.") : null));
}

function sendCompleteCheck(stat, callback) {
    const bomId = document.getElementById("bomId").value;
    ajaxRequest("/materialflow/send-complete/" + bomId + "/" + stat, "get", null, (c) => {
        alert(c.message);
        callback(c.result);
        if(c.result) ajaxRequest("/materialflow/send-sms/" + bomId, "post");
    }, (e) => console.log(e));
}

function ajaxRequest(url, method, data, callback, err) {
    $.ajax({
        url: url,
        type: method,
        data: method.toUpperCase() !== 'get' ? JSON.stringify(data) : undefined, // get 요청이 아닐 때만 데이터 본문 포함
        contentType: 'application/json',
        success: function (r) {
            callback(r);
        }, error: function (x, e, r) {
            console.error(x);
            console.error(e);
            console.error(r);
            err(x.responseJSON);
        }
    })
}