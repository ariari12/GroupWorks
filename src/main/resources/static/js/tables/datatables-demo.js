// Call the dataTables jQuery plugin
$(document).ready(function() {

  // 테이블 생성
  $('#datatablesSimple').DataTable({
      info: false,
      ordering: true,
      order: [[3, 'desc']],
      scrollX: true,
      paging: true,
      scrollCollapse: true,
      scrollY: 450,
  });
});
