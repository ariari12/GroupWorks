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
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');">';
            htmlData += '<i class="far fa-minus-square"></i></a>';
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

/* 첨부파일 전송 */
function fileSend(formData, pk) {

    /* 첨부 파일 담기 */
    if(filesArr.length > 0) {
        for (var i = 0; i < filesArr.length; i++) {
            // 삭제되지 않은 파일만 폼데이터에 담기
            if (!filesArr[i].is_delete) {
                formData.append("attach_file", filesArr[i]);
            }
        }
        formData.append("pk", pk);

        $.ajax({
            url: "/work-flow/file-send",
            type: "post",
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                // alert("결재 요청 발송");
                // window.location = url;
            },
            error: function() {
                alert("파일 전송 실패!");
            }
        });
    }
}

$(() => {
    $("#approveForm").submit(function(event) {
        event.preventDefault(); // 기본 폼 제출 동작을 막음

        // 폼데이터 담기
        var form = $(this);
        var formData = new FormData(form[0]);

        console.dir(form);
        console.dir(formData);

        // 폼 데이터를 서버로 전송하여 유효성 검사 수행
        $.ajax({
            url: form.attr('action'),
            type: form.attr('method'),
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {

                if (response.valid) {
                    fileSend(formData, response.primaryKey);
                    approversSendList(response.primaryKey);

                    alert("결재 발송");
                } else {
                    alert("결재 요청 정보를 기입해주세요.");
                    // 유효성 검사 실패 시 에러 메시지 표시 또는 다른 처리
                }
                // window.location = response.url;
            },
            error: function() {
                alert("결재 발송 실패!");
            }
        });
    });
});