document.addEventListener("DOMContentLoaded", function (ev) {
    document.getElementById("calculate").addEventListener("submit", function (e) {
        e.preventDefault();

        const start = document.getElementById("start").value;
        const end = document.getElementById("end").value;

        location.href = "/materialflow/sales-calculi?start=" + start + "&end=" + end;
    })
})


function load(pk, c) {
    window.location = "/materialflow/order-detail/" + pk + "/" + c;
}
