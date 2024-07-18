// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

function circle1(labels, data) {
    // 캔버스 태그 추출
    var ctx = document.getElementById('myCircleChart').getContext('2d');
    
    // 사용자 입력 데이터
    var colors = [
        'rgba(255, 99, 132, 0.6)',
        'rgba(54, 162, 235, 0.6)',
        'rgba(255, 206, 86, 0.6)',
        'rgba(75, 192, 192, 0.6)',
        'rgba(153, 102, 255, 0.6)',
        'rgba(255, 159, 64, 0.6)'
    ];
    
    // 원형 차트 생성
    return new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
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

function circle2(labels, data) {
    // 두 번째 원형 차트
    var ctx2 = document.getElementById('myCircleChart2').getContext('2d');

    // 두 번째 원형 차트 데이터 설정 (다른 색상 사용)
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
            labels: labels,
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

function MyApprovalStats(labels, data1, data2, data3) {
    // Area Chart Example
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
            // 그래프 라벨
            labels: labels,
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
                    ticks: { maxTicksLimit: 7 }
                }],
                yAxes: [{
                    ticks: {
                        min: 0, max: 40000, maxTicksLimit: 5
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
    var ctx = document.getElementById("myBarChart");
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
                    ticks: { maxTicksLimit: 6 }
                }],
                yAxes: [{
                    ticks: {
                        min: 0, max: 15000, maxTicksLimit: 5
                    },
                    gridLines: { display: true }
                }],
            },
            legend: { display: false }
        }
    });
}