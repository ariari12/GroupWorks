// Call the dataTables jQuery plugin
$(document).ready(function() {

    // 결재 대기 목록 테이블 생성
    $('#approvalWaitTable').DataTable({
        info: false,
        ordering: true,
        order: [[5, 'desc']],
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 10, 15, 20, 25, 30 ],
        displayLength: 15,
    });

    // 승인한 결재 목록 테이블 생성
    $('#approvedListTable').DataTable({
        info: false,
        ordering: true,
        order: [[5, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 10, 15, 20, 25, 30 ],
        displayLength: 15,
    });

    // 반려한 결재 목록 테이블 생성
    $('#rejectionListTables').DataTable({
        info: false,
        ordering: true,
        order: [[5, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 10, 15, 20, 25, 30 ],
        displayLength: 15,
    });
});

function load(pk) {
    window.location = "/work-flow/detail/" + pk;
}
