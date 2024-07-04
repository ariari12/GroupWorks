// Call the dataTables jQuery plugin
$(document).ready(function() {

    // 내 결재 요청 목록 테이블 생성
    $('#MyRequestList').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });

    // 승인된 결재 목록 테이블 생성
    $('#approvedTable').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });

    // 내 결재 요청 대기 목록 테이블 생성
    $('#MyRequestWaitTable').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });

    // 결재 승인 대기 목록 테이블 생성
    $('#approvalWaitTable').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
    });
});
