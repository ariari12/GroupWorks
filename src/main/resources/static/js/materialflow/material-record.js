document.addEventListener('DOMContentLoaded', function() {
    tableSet();
});

function tableSet() {
    $('#table').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 690,
        lengthMenu: [ 20, 30, 40, 50, 75, 100 ],
        displayLength: 30,
    });
}
