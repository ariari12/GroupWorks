var fileNo = 0;
var filesArr = [];

/* 파일 초기화 */
function resetFile() {
  const fileList = $("#file-list");
  // 기존 첨부 파일 초기화
  fileList.html("");
  // 스타일 제거
  fileList.removeClass("file-list");
  // 초기화
  document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 추가 */
function addFile(obj){

  var maxFileCnt = 10;   // 첨부파일 최대 개수
  var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
  var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
  var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

  // 첨부파일 개수 확인
  if (curFileCnt > remainFileCnt) {
    alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
  }

  for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {
    const file = obj.files[i];

    // 첨부파일 검증
    if (validation(file)) {
      // 파일 배열에 담기
      var reader = new FileReader();
      reader.onload = function () {
        filesArr.push(file);
      };
      reader.readAsDataURL(file)

      // 목록 추가
      $("#file-list").addClass("file-list");
      let htmlData = '';
      htmlData += '<div id="file' + fileNo + '" class="filebox">';
      htmlData += '   <p class="name">' + file.name + '</p>';
      htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
      htmlData += '</div>';
      $('.file-list').append(htmlData);
      fileNo++;
    }
  }
  // 초기화
  document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 검증 */
function validation(obj){
  // console.dir(obj);
  if (obj.name.length > 100) {
    alert("파일명이 100자 이상인 파일은 제외되었습니다.");
    return false;
  } else if (obj.size > (100 * 1024 * 1024)) {
    alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
    return false;
  } else if (obj.name.lastIndexOf('.') === -1) {
    alert("확장자가 없는 파일은 제외되었습니다.");
    return false;
  } else {
    const fileTypes = ['.zip', '.pptx', '.docx', '.csv', '.txt', '.xlsx', ".mp4", ".mp3", '.pdf', '.gif', '.jpeg', '.png', '.bmp', '.tif', '.hwp'];
    for (const ft of fileTypes) {
      if (obj.name.endsWith(ft)) {
        return true;
      }
      // console.log(obj.name, ": ", ft);
    }
    alert("첨부가 불가능한 파일은 제외되었습니다.");
    return false;
  }
}

/* 첨부파일 삭제 */
function deleteFile(num) {
  document.querySelector("#file" + num).remove();
  filesArr[num].is_delete = true;
}

/* 첨부 파일 전송 */
function submitForm() {
  
  // 폼데이터 담기
  var form = document.querySelector("form");
  var formData = new FormData(form);
  
  if(filesArr.length > 0) {
    for (var i = 0; i < filesArr.length; i++) {
      // 삭제되지 않은 파일만 폼데이터에 담기
      if (!filesArr[i].is_delete) {
        formData.append("attach_file", filesArr[i]);
      }
    }
    
    console.dir("form: ", form);
    console.dir("formData: ", formData);
    
    $.ajax({
      method: 'POST',
      url: '/work-flow/file-send',
      dataType: 'json',
      data: formData,
      async: true,
      timeout: 30000,
      cache: false,
      headers: {'cache-control': 'no-cache', 'pragma': 'no-cache'},
      processData: false,  // jQuery가 데이터를 처리하지 않도록 설정
      contentType: false,  // jQuery가 Content-Type 헤더를 설정하지 않도록 설정
      success: function (data) {
        alert("결재 요청 전송");
        console.log(data);
      },
      error: function (xhr, desc, err) {
        alert('결재 요청 실패');
        console.log(xhr);
        console.log(desc);
        console.log(err);
      }
    })
  }
}