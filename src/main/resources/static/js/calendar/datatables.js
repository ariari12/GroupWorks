// Call the dataTables jQuery plugin
$(document).ready(function() {

  // 테이블 생성
  $('#vacationMyHistory').DataTable({
      info: false,
      ordering: true,
      order: [[3, 'desc']],
      scrollX: true,
      paging: true,
      scrollCollapse: true,
      scrollY: 450,
  });

    $('#vacationRequest').DataTable({
        info: false,
        ordering: true,
        order: [[3, 'desc']],
        scrollX: true,
        paging: true,
        scrollCollapse: true,
        scrollY: 450,
        layout: {
            bottomEnd: {
                paging: {
                    firstLast: false
                }
            }
        }
    });

});
