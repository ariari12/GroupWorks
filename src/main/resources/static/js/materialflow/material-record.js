document.addEventListener('DOMContentLoaded', function (e) {
    tableSet();
    materialStatusUpdate();
    itemCodeTableSet();
});

function tableSet() {
    $('#table').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
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
        // info: false,
        // ordering: true,
        // order: [[0, 'desc']],
        paging: true,
        scrollX: true,
        // scrollCollapse: true,
        scrollY: 260,
        lengthMenu: [ 5, 10, 15, 20, 25, 30 ],
        displayLength: 5,
    });
}

function materialStatusUpdate() {
    document.getElementById('statusUpdateFrm').addEventListener('submit', ev => {
        ev.preventDefault();

        const t = ev.target;
        const data = {
            itemCodeList: itemCodeList,
            item: {
                itemStatus: document.querySelector('#statusUpdateFrm input[name="itemStatus"]:checked').value,
                storageManager: t[4].value,
                storageLocation: t[5].value,
                storageTime: t[6].value,
                deliveryManager: t[7].value,
                deliveryLocation: t[8].value,
                deliveryTime: t[9].value
            }
        };
        console.log(data);
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