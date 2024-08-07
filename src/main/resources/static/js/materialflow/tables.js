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
            lengthMenu: [ 10, 15, 20, 25, 30 ],
            displayLength: 15,
        });
    }
});

function load(pk) {
    window.location = "/material/detail/" + pk;
}
