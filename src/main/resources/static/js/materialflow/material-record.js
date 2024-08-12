document.addEventListener('DOMContentLoaded', function (e) {
    tableSet();
    materialStatusUpdate();
    itemCodeTableSet();
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
        scrollY: 260,
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

        const t = ev.target;

        const data = {
            itemCodeList: itemCodeList,
            item: {
                "itemStatus": parseInt(document.querySelector('#statusUpdateFrm input[name="itemStatus"]:checked').value),
                "storageManager": t[9].value,
                "storageLocation": t[10].value,
                "storageTime": t[11].value,
                "deliveryManager": t[12].value,
                "deliveryLocation": t[13].value,
                "deliveryTime": t[14].value
            }
        };

        ajaxRequest("/materialflow/material", "put", data, function (r) {
            console.log(r);
            if(r.result) {
                alert(r.message);
            } else {
                alert(r.message);
            }
            modal.hide();
            location.href = window.location.pathname;
        }, function (e) {
            modal.hide();
        });
    })
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
            err(x.responseJSON);
        }
    })
}