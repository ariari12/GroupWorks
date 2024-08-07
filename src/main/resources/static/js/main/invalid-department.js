const params = new URLSearchParams(window.location.search);
if (params.get('alert') === 'invalid_department') {
  alert('접근 불가능한 부서입니다. 메인 화면으로 이동합니다.');
}