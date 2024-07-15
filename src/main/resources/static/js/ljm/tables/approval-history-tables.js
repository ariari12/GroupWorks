// Call the dataTables jQuery plugin
$(document).ready(function() {

    // 내 결재 승인 목록 테이블 생성
    $('#MyApprovedTable').DataTable({
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

    // 내 결재 진행 중인 목록 테이블 생성
    $('#MyProgressTable').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 10, 15, 20, 25, 30 ],
        displayLength: 15,
    });

    // 내 결재 반려 목록 테이블 생성
    $('#MyRejectionList').DataTable({
        info: false,
        ordering: true,
        order: [[0, 'desc']],
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
