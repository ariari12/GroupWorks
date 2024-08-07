// Call the dataTables jQuery plugin
$(document).ready(function() {
    // 결재 협조 목록
    table1();
    // 결재 참조 목록
    table2();
    // 테이블 hover 이벤트 등록
    tableHover();
});

function table1() {
    // 결재 협조 목록
    $('#collaboratorListTable').DataTable({
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
}

function table2() {
    // 결재 참조 목록
    $('#referrerListTable').DataTable({
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
}


function load(pk) {
    window.location = "/work-flow/detail/" + pk;
}
