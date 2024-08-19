$(document).ready(function() {
    $('#table').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 20, 30, 40, 50, 75, 100 ],
        displayLength: 30,
    });
});


function orderTabMove(url) {
    window.open(url);
}

function bomWindow(url) {
    window.open(url, "BOM 자재 현황",
        "width=1400, height=1200, left=300, top=30");
}
