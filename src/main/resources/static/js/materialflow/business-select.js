document.addEventListener('DOMContentLoaded', ev => {
    $('#table').DataTable({
        info: false,
        ordering: true,
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 690,
        lengthMenu: [ 10, 20, 30, 40, 50, 100 ],
        displayLength: 40,
    });
});

function sendDataToParent(tr) {
    // tr 요소에서 데이터 추출
    const inputs = tr.querySelectorAll('td input');
    const data = {
        id: inputs[0].value,
        businessNumber: inputs[1].value,
        businessName: tr.children[1].innerText,
        type: tr.children[3].innerText,
        item: tr.children[4].innerText,
    };
    
    // JSON으로 변환하여 부모 창으로 전송
    window.opener.postMessage(data, window.location.origin);
    window.close();
}

function delClick(event, pk) {
    event.stopPropagation();

    const modal = new bootstrap.Modal(document.getElementById('modal'));
    modal.show();
    document.getElementById("cns-Btn").addEventListener("click", e => modal.hide(), { once : true });
    document.getElementById('okBtn').addEventListener("click", function (e) {
        const t = event.target;
        ajaxRequest("/materialflow/business-select/" + pk, "delete", null, function (c) {
            if(c.result) {
                alert(c.message);
            } else {
                alert(c.message);
            }
            modal.hide();
            location.href = "/materialflow/business-select";
        }, function (e) {
            modal.hide();
            alert(e.responseText);
        })
    }, { once : true });
}

function ajaxRequest(url, method, data, callback, err) {
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: (r) => callback(r),
        error: function (x, e, r) {
            console.error(x);
            console.error(e);
            console.error(r);
            err(x.responseJSON);
        }
    })
}