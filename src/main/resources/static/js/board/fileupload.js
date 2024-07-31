var filesArr = [];
var fileNo = 0; // fileNo 초기화

/* 첨부파일 추가 */
function addFile(obj) {
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    } else {
        for (const file of obj.files) {
            // 첨부파일 검증
            if (validation(file)) {
                // 파일 배열에 담기
                filesArr.push({...file, is_delete: false});
                // 목록 추가
                let htmlData = '';
                htmlData += '<div id="file' + fileNo + '" class="filebox" data-file="file">';
                htmlData += '   <p class="name">' + file.name + '</p>';
                htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');" style="text-decoration: none; color: red;">';
                htmlData += '       <span style="font-size: 20px; font-weight: bold;">&times;</span>'; // '×' (multiplication sign)
                htmlData += '   </a>';
                htmlData += '</div>';
                $('.file-list').append(htmlData);
                fileNo++;
            }
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/* 첨부파일 검증 */
function validation(file) {
    const fileTypes = [
        'application/pdf',                  // PDF 파일
        'image/gif',                        // GIF 이미지
        'image/jpeg',                       // JPEG 이미지
        'image/png',                        // PNG 이미지
        'image/bmp',                        // BMP 이미지
        'image/tif',                        // TIFF 이미지
        'text/csv',                         // CSV 파일
        'text/plain',                       // 텍스트 파일
        'application/zip',                  // ZIP 압축 파일
        'application/x-zip-compressed',     // ZIP 압축 파일
        'application/haansofthwp',          // 한글 워드 프로세서 파일
        'application/x-hwp',                // 한글 워드 프로세서 파일
        'application/msword',               // 구 버전 MS Word 파일
        'application/vnd.openxmlformats-officedocument.wordprocessingml.document', // 최신 MS Word 파일
        'application/vnd.ms-excel',         // 구 버전 MS Excel 파일
        'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', // 최신 MS Excel 파일 (XLSX)
        'application/vnd.ms-powerpoint',    // 구 버전 MS PowerPoint 파일
        'application/vnd.openxmlformats-officedocument.presentationml.presentation', // 최신 MS PowerPoint 파일
        'application/vnd.oasis.opendocument.text', // OpenDocument Text 파일
        'application/vnd.oasis.opendocument.spreadsheet', // OpenDocument Spreadsheet 파일
        'application/vnd.oasis.opendocument.presentation', // OpenDocument Presentation 파일
        'application/vnd.adobe.flash.movie', // Adobe Flash 파일
        'application/vnd.adobe.photoshop',  // Adobe Photoshop 파일
        'application/json',                 // JSON 데이터 파일
        'application/xml',                  // XML 데이터 파일
    ];
    if (file.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (file.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (!file.name.includes('.')) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(file.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/* 첨부파일 삭제 */
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    if (filesArr[num]) {
        filesArr[num].is_delete = true;
    }
}

/* 폼 전송 */
function submitForm() {
    // 폼데이터 담기
    var form = document.querySelector("form");
    var formData = new FormData(form);
    for (var i = 0; i < filesArr.length; i++) {
        // 삭제되지 않은 파일만 폼데이터에 담기
        if (!filesArr[i].is_delete) {
            formData.append("attach_file", filesArr[i]);
        }
    }

    $.ajax({
        method: 'POST',
        url: '/register',
        dataType: 'json',
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            alert("파일업로드 성공");
        },
        error: function (xhr, desc, err) {
            alert('에러가 발생 하였습니다.');
        }
    });
}