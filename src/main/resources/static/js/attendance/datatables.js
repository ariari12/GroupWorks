// Call the dataTables jQuery plugin
$(document).ready(function() {

    // 근태관리 테이블
    $('#datatablesCommute').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'asc']],
        scrollX: true,
        scrollCollapse: true,
        scrollY: 450,
        dom: 'rtip',
        pageLength: 31,
        paging: false,
        columnDefs: [
            { className: 'text-center', targets: '_all' }
        ]
    });
});