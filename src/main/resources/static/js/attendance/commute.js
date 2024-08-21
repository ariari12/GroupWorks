$(document).ready(function(){
    dayWeek = ["일", "월", "화", "수", "목", "금", "토"];

    function updateTime() {
        const today = new Date();

        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const date = String(today.getDate()).padStart(2, '0');
        const day = today.getDay();

        const hours = String(today.getHours()).padStart(2, '0');
        const minutes = String(today.getMinutes()).padStart(2, '0');
        const seconds = String(today.getSeconds()).padStart(2, '0');

        $("#date").text(`${year}-${month}-${date}(${dayWeek[day]})`);
        $("#time").text(`${hours}:${minutes}:${seconds}`);
    }
    updateTime();
    setInterval(updateTime, 500);
    
    
    // 출, 퇴근 버튼.
    $('#commute-btn').on('click', function () {

        let msg = $('#commute-btn').val().slice(0, 2);
        let type = '';

        if (confirm(msg + "하시겠습니까?")) {
            if ($('#commute-btn').val() === '출근하기') {
                $.ajax({
                    type: 'POST',
                    url: '/api/commute/clock-in',
                    contentType: 'application/json',
                    success: function(response) {
                        alert('출근이 기록되었습니다!');
                        $('#commute-btn').val('퇴근하기');
                    },
                    error: function(xhr, status, error) {
                        if (xhr.status === 400) {
                            alert('오늘 이미 출근 기록이 있습니다.');
                        } else {
                            alert('출근 기록에 실패했습니다: ' + error);
                        }
                    }
                });

            } else if($('#commute-btn').val() === '퇴근하기') {

                $.ajax({
                    type: 'POST',
                    url: '/api/commute/clock-out',
                    contentType: 'application/json',
                    success: function(response) {
                        alert('퇴근이 기록되었습니다!');
                        $('#commute-btn').val('✅ 퇴근 완료').attr("disabled", true);
                    },
                    error: function(xhr, status, error) {
                        if (xhr.status === 400) {
                            alert('오늘 이미 퇴근 기록이 있습니다.');
                        } else {
                            alert('퇴근 기록에 실패했습니다: ' + error);
                        }
                    }
                });

            } else {
                alert('🎉 오늘의 출근 및 퇴근이 모두 완료되었습니다!');
            }
        }
    });


    // 출퇴근 버튼
    $.ajax({
        url: '/api/commute/status',
        method: 'GET',
        success: function(response) {
            if (response === "CLOCKED_OUT") {   // 출, 퇴근 완료.
                $('#commute-btn').val('✅ 퇴근 완료').attr("disabled", true);
            } else if (response === "CLOCKED_IN") {
                $('#commute-btn').val('퇴근하기');  // 퇴근
            } else {
                $('#commute-btn').val('출근하기');  // 출근
            }
        }
    });
});
