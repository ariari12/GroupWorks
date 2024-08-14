document.addEventListener('DOMContentLoaded', ev => {
    dataTableSetup();
    managerAddSubmit();
});

function dataTableSetup() {
    $('#table').DataTable({
        info: false,
        ordering: true,
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 223,
        lengthMenu: [ 10, 20, 30, 40, 50, 100 ],
        displayLength: 40,
    });
}

function sendDataToParent(tr) {
    const inputs = tr.querySelectorAll('td input');
    const data = {
        id: inputs[0].value,
        name: inputs[1].value,
        phone: tr.children[2].innerText,
        email: tr.children[3].innerText,
    };
    window.opener.postMessage(data, window.location.origin);
    window.close();
}

function managerAddSubmit() {
    document.getElementById("managerForm").addEventListener("submit", ev => {
        ev.preventDefault();

        const t = ev.target;
        const data = {
            id: 0,
            name: t[0].value,
            phone: t[1].value,
            email: t[2].value
        };
        const businessId = document.getElementById('businessId').value;
        ajaxRequest(t.action + "?businessId=" + businessId, t.method, data, c => {
            window.opener.postMessage(c, window.location.origin);
            window.close();
        }, (x) => {
            let errMsg = "";
            for (const fieldErrorsKey in x.fieldErrors) {
                errMsg += x.fieldErrors[fieldErrorsKey] + "\n";
            }
            alert(errMsg);
        });
    });
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

// 삭제 클릭 시 tr 이벤트 전파 중단
function delClick(event, pk) {
    event.stopPropagation();

    const modal = new bootstrap.Modal(document.getElementById('modal'));
    modal.show();
    document.getElementById("cns-Btn").addEventListener("click", e => modal.hide(), { once : true });
    document.getElementById('okBtn').addEventListener("click", function (e) {
        const t = event.target;
        ajaxRequest("/materialflow/manager-select/" + pk, "delete", null, function (c) {
            if(c.result) {
                alert(c.message);
            } else {
                alert(c.message);
            }
            modal.hide();
            location.href = "/materialflow/manager-select/" + document.getElementById('businessId').value;
        }, function (e) {
            modal.hide();
            alert(e.responseText);
        })
    }, { once : true });
}
