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
});