document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('findAddress').addEventListener("click", e => addresRoad());
    document.getElementById('frmSub').addEventListener("submit",  function (e) {submit(e)});
})

function ajaxRequest(url, method, data, callback, err) {
    $.ajax({
        url: url,
        type: method,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (r) {
            callback(r);
        }, error: function (x, e, r) {
            console.error(x);
            console.error(e);
            console.error(r);
            err(x.responseText);
        }
    })
}

function addresRoad() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우 참고항목을 조합한다.
                addr = data.roadAddress;
                var addrRef = '';
                // 법정동명이 있을 경우 추가한다. (법정리는 제외) 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    addrRef += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    addrRef += (addr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(addrRef !== '') {
                    addr += ' (' + addrRef + ')';
                }
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            document.querySelector("input[name='address']").value = addr;
            document.querySelector("input[name='detailAddress']").focus();
        }
    }).open();
}

function submit(event) {
    event.preventDefault(); // 기본 폼 제출 동작을 막음

    // 폼데이터 담기
    const t = event.target;
    const data = {
        id: 0,
        businessNumber: t[0].value,
        businessName: t[1].value,
        ceo: t[2].value,
        type: t[3].value,
        item: t[4].value,
        ceoTel: t[5].value,
        address: document.querySelector("input[name='address']").value + " " + document.querySelector("input[name='detailAddress']").value,
        fax: t[9].value
    };

    ajaxRequest(t.action, t.method, data, function (d) {
        alert("거래처 등록 완료");
        window.opener.postMessage(d, window.location.origin);
        window.close();
    }, function (msg) {
        let errMsg = "거래처 등록 실패.\n" + msg;
        alert(errMsg);
    })
}