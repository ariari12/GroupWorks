// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

document.addEventListener("DOMContentLoaded", function() {

    /* 부서별 결재 발송, 승인, 결재 건 */
    getAjax("/work-flow/stat/1", function (data) {
        approvalStatsByDepartment(
            data.departmentList,
            data.numberList.request,
            data.numberList.approve,
            data.numberList.reject
        );
    });
    /* 올해 전체 결재 발송 구분 */
    getAjax("/work-flow/stat/2", totalApproveByThisYear);
    /* 월 별 결재 발송/승인/반려 건 */
    getAjax("/work-flow/stat/4", function (data) {
        MyApprovalStats(data.request, data.approval, data.rejection);
    });
    /* 내 누적 결재 발송 건 구분 */
    getAjax("/work-flow/stat/5", totalApproval);
});

function getAjax(url, callback) {
    $.ajax({
        url: url,
        type: 'get',
        success: function (res) {
            callback(res);
        }, error: function (x, e, s) {
            console.error(x);
            console.error(e);
            console.error(s);
        }
    });
}

/* 내 누적 결재 발송 건 구분 */
function totalApproval(data) {
    // 캔버스 태그 추출
    var ctx = document.getElementById('totalApproval').getContext('2d');

    // 색상
    var colors = [
        'rgba(255, 99, 132, 0.6)',
        'rgba(54, 162, 235, 0.6)',
        'rgba(255, 206, 86, 0.6)',
        'rgba(75, 192, 192, 0.6)',
        'rgba(153, 102, 255, 0.6)',
        'rgba(255, 159, 64, 0.6)'
    ];
    
    return new Chart(ctx, {
        type: 'pie',
        data: {
            labels: ['업무', '재무', '예산', '구매', '보고', '특별'],
            datasets: [{
                data: data,
                backgroundColor: colors,
                hoverBackgroundColor: colors
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false
        }
    });
}

/* 올해 전체 결재 발송 구분 */
function totalApproveByThisYear(data) {
    var ctx2 = document.getElementById('totalApproveByThisYear').getContext('2d');

    // 색상
    var colors2 = [
        'rgba(255, 0, 0, 0.6)',
        'rgba(0, 255, 0, 0.6)',
        'rgba(0, 0, 255, 0.6)',
        'rgba(255, 255, 0, 0.6)',
        'rgba(255, 0, 255, 0.6)',
        'rgba(0, 255, 255, 0.6)'
    ];

    var chart2 = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: ['업무 건', '재무 건', '예산 건', '구매 건', '보고 건', '특별 건'],
            datasets: [{
                data: data,
                backgroundColor: colors2,
                hoverBackgroundColor: colors2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false
        }
    });
}

/* 월 별 결재 발송/승인/반려 건 */
function MyApprovalStats(data1, data2, data3) {
    // Area Chart Example
    var ctx = document.getElementById("monthlyApprova");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            // 그래프 라벨
            labels: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            datasets: [
                {
                    label: "발송 건",
                    lineTension: 0.3,
                    backgroundColor: "rgba(2,117,216,0.2)",
                    borderColor: "rgba(2,117,216,1)",
                    pointRadius: 5,
                    pointBackgroundColor: "rgba(2,117,216,1)",
                    pointBorderColor: "rgba(255,255,255,0.8)",
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "rgba(2,117,216,1)",
                    pointHitRadius: 50,
                    pointBorderWidth: 2,
                    // 그래프 value
                    data: data1,
                },
                {
                    label: "승인 건",
                    lineTension: 0.3,
                    backgroundColor: "rgba(153, 102, 255, 0.2)",
                    borderColor: "rgba(153, 102, 255, 1)",
                    pointRadius: 5,
                    pointBackgroundColor: "rgba(75, 192, 192, 0.6)",
                    pointBorderColor: "rgba(75, 192, 192, 1)",
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "rgba(75, 192, 192, 1)",
                    pointHitRadius: 50,
                    pointBorderWidth: 2,
                    // 그래프 value
                    data: data2,
                },
                {
                    label: "반려 건",
                    lineTension: 0.3,
                    backgroundColor: "rgba(153, 102, 255, 0.2)",
                    borderColor: "rgba(153, 102, 255, 1)",
                    pointRadius: 5,
                    pointBackgroundColor: "rgba(153, 102, 255, 0.6)",
                    pointBorderColor: "rgba(153, 102, 255, 1)",
                    pointHoverRadius: 5,
                    pointHoverBackgroundColor: "rgba(153, 102, 255, 1)",
                    pointHitRadius: 50,
                    pointBorderWidth: 2,
                    // 그래프 value
                    data: data3,
                }
            ],
        },
        options: {
            scales: {
                xAxes: [{
                    time: { unit: 'date' },
                    gridLines: { display: false },
                    ticks: { }
                }],
                yAxes: [{
                    ticks: {
                        min: 0, maxTicksLimit: findMaxValue([data1, data2, data3])
                    },
                    gridLines: { color: "rgba(0, 0, 0, .125)", }
                }],
            },
            legend: {
                display: false
            }
        }
    });
}

/* 부서별 결재 발송, 승인, 결재 건 */
function approvalStatsByDepartment(labels, data1, data2, data3) {
    // Bar Chart Example
    var ctx = document.getElementById("approvalStatsByDepartment");
    var myLineChart = new Chart(ctx, {
        type: 'bar',
        data: {
            // 차트 라벨
            labels: labels,
            datasets: [
                {
                    label: "발송 건",
                    backgroundColor: "rgba(2,117,216,1)",
                    borderColor: "rgba(2,117,216,1)",
                    // 차트 value
                    data: data1,
                },
                {
                    label: "승인 건",
                    backgroundColor: "rgba(255,99,132,0.8)",
                    borderColor: "rgba(255,99,132,1)",
                    // 차트 value
                    data: data2,
                },
                {
                    label: "반려 건",
                    backgroundColor: "rgba(75,192,192,0.8)",
                    borderColor: "rgba(75,192,192,1)",
                    // 차트 value
                    data: data3,
                }
            ],
        },
        options: {
            scales: {
                xAxes: [{
                    time: { unit: 'month' },
                    gridLines: { display: false },
                    ticks: { maxTicksLimit: labels.length }
                }],
                yAxes: [{
                    ticks: {
                        min: 0, maxTicksLimit: findMaxValue([data1, data2, data3])
                    },
                    gridLines: { display: true }
                }],
            },
            legend: { display: false }
        }
    });
}

/* data 최대 값에 따른 범위 */
function findMaxValue(datasets) {
    let maxValue = -Infinity;
    datasets.forEach(data => {
        const datasetMax = Math.max(...data);
        if (datasetMax > maxValue) {
            maxValue = datasetMax;
        }
    });

    if (maxValue <= 10) {
        return 3;
    } else if (maxValue <= 50) {
        return 10;
    } else if (maxValue <= 100) {
        return 15;
    } else {
        return 20;
    }
}

// Call the dataTables jQuery plugin
$(document).ready(function() {
    // 부서 승인 결재 목록
    $('#departmentApprovedTable').DataTable({
        info: false,
        ordering: true,
        order: [[5, 'desc']],
        paging: true,
        scrollX: true,
        scrollCollapse: true,
        scrollY: 450,
        lengthMenu: [ 10, 15, 20, 25, 30 ],
        displayLength: 15,
    });
});

function load(pk) {
    window.location = "/work-flow/detail/" + pk;
}
