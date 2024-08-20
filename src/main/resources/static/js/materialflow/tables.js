// Call the dataTables jQuery plugin
$(document).ready(function() {
    for (let i = 1; i <= 4; i++) {
        $('#table' + i).DataTable({
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
    }
});

function load(pk, c) {
    window.location = "/materialflow/order-detail/" + pk + "/" + c;
}
