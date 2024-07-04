// Call the dataTables jQuery plugin
$(document).ready(function() {

    // 테이블 생성
    $('#datatablesSimple').DataTable({
        info: false,
        ordering: true,
        order: [[1, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });

    // 테이블 생성
    $('#approvalWait02').DataTable({
        info: false,
        ordering: true,
        order: [[1, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });

    // 테이블 생성
    $('#approvalWait03').DataTable({
        info: false,
        ordering: true,
        order: [[1, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });
});
