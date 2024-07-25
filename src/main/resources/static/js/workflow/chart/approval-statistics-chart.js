// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

document.addEventListener("DOMContentLoaded", function() {
    approvalStatsByDepartment(
        ["기술부서", "마케팅부서", "영업부서", "인사부서", "재무부서", "IT 지원부서",
            "제품 관리부서", "고객 서비스부서", "법무부서", "연구개발부서"],
        [4215, 8417, 6251, 5312, 9821, 14984, 2154, 7841, 5162, 3125,],
        [2154, 7841, 5162, 3125, 8219, 12984, 2154, 7841, 5162, 3125,],
        [1542, 4178, 2516, 1253, 2189, 9841, 2154, 7841, 5162, 3125,]
    );
    MyApprovalStats(
        [33259, 30162, 33259, 30274, 33451, 32651, 31274, 34984, 38451, 36162, 32651, 31984],
        [28682, 25849, 24159, 21849, 24159, 24849, 23159, 26682, 28849, 27682, 25849, 24159],
        [18394, 18682, 14000, 13394, 12287, 10000, 11591, 12394, 13940, 14839, 15394, 16394]
    );
    totalApproval(
        [12, 19, 3, 5, 2, 3]
    );
    totalApproveByThisYear(
        [330000, 1900000, 200000, 500000, 1000000, 300000]
    );
});

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
            labels: ['업무 건', '재무 건', '예산 건', '구매 건', '보고 건', '특별 건'],
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
                    ticks: {  }
                }],
                yAxes: [{
                    ticks: {
                        min: 0, maxTicksLimit: 100
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
                        min: 0, maxTicksLimit: 15
                    },
                    gridLines: { display: true }
                }],
            },
            legend: { display: false }
        }
    });
}