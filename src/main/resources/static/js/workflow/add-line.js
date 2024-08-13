let approvIdx = 0;
let collabIdx = 0;
let referrIdx = 0;
let employeeNum;
let addLine;
let memberList = [];

function getEmployeeAll() {
    $.ajax({
        url: "/openapi/work-flow/employee",
        type: "GET",
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        success: function (response) {
            // console.dir(response);
            if(response.result) {
                memberList = response.employee;
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

window.onload = () => {
    getEmployeeAll();

    let searchForm = $("#search"); // 검색 입력
    let suggestion = $(".dropdown-menu"); // 추천 키워드

    searchForm.keyup(function (e) {
        suggestion.empty();

        for (const item of memberList) {
            if(item.name.indexOf(this.value) > -1 || item.departmentName.indexOf(this.value) > -1 || item.rank.indexOf(this.value) > -1) {
                suggestion.append(
                '<li class="dropdown-item" onclick="selectVal(this);" value="' + item.id +'"">'
                + "사원: " + item.name + " 부서: " + item.departmentName + ' 직급: ' + item.rank
                );
            }
        }
    });

    $("#add-member").on("click", function() {approveAdd()});
    $("#add-member2").on("click", function() {collaborateAdd()});
    $("#add-member3").on("click", function() {referrerAdd()});
}

// 추천 키워드 추가
function selectVal(option) {
    if(approvIdx >= 5 && approvIdx >= 5 && collabIdx >= 5) {
        alert("더 이상 추가할 수 없습니다.");
        return;
    }
    // console.dir(option.value);
    employeeNum = option.value;
    $("#search").val(option.innerText);
}

// 결재라인 추가
function approveAdd() {
    if(approvIdx >= 5) {
        alert("결재인은 최대 5명까지만 입력 가능합니다.");
        return;
    }
    addLine = $("#approval-line");
    let memberItem = addMember(
        approvIdx, "approves", "approveCancel");

    if(memberItem != null) {
        addLine.append(memberItem);
        approvIdx++;
    }

    // 결재자 수 변경
    $("#approverCount").val(approvIdx);
}

// 협조라인 추가
function collaborateAdd() {
    if(collabIdx >= 5) {
        alert("협조인은 최대 5명까지만 입력 가능합니다.");
        return;
    }
    addLine = $("#collaborator-line");
    let memberItem = addMember(
        collabIdx, "collaborator", "collaboratorCancel");

    if(memberItem != null) {
        addLine.append(memberItem);
        collabIdx++;
    }
}

// 참조라인 추가
function referrerAdd() {
    if(referrIdx >= 5) {
        alert("참조인은 최대 5명까지만 입력 가능합니다.");
        return;
    }
    addLine = $("#referrer-line");
    let memberItem = addMember(
        referrIdx, "referrer", "referrerCancel");

    if(memberItem != null) {
        addLine.append(memberItem);
        referrIdx++;
    }
}

// 라인 추가
function addMember(idx, name, func) {
    // console.log(employeeNum);
    if(employeeNum === undefined) {
        alert("등록되어 있는 사원정보를 입력해주세요.");
        return null;
    }

    let searchForm = $("#search"); // 검색 입력

    let add = '<li class="list-group-item">';
    add += '<span>' + (idx +1) + '</span> ';
    add += searchForm.val();
    add += '<input type="hidden" name="' + name + '" value="' + employeeNum + '">';
    add += '<button type="button" class="btn-close" aria-label="Close" onclick="' + func + '(' + idx + ')">';
    add += '</button></li>';

    employeeNum = undefined;
    searchForm.val("");
    return add;
}

// 결재라인 제거
function approveCancel(i) {
    const FUNCTIONAL_NAME = "approveCancel";
    const NAME = 'approves';
    cancelLine(i, "#approval-line", NAME, FUNCTIONAL_NAME);
    approvIdx--;

    // 결재자 수 변경
    $("#approverCount").val(approvIdx);
}
// 협조라인 제거
function collaboratorCancel(i) {
    const FUNCTIONAL_NAME = "collaboratorCancel";
    const NAME = 'collaborator';
    cancelLine(i, "#collaborator-line", NAME, FUNCTIONAL_NAME);
    collabIdx--;
}
// 참조라인 제거
function referrerCancel(i) {
    const FUNCTIONAL_NAME = "referrerCancel";
    const NAME = 'referrer';
    cancelLine(i, "#referrer-line", NAME, FUNCTIONAL_NAME);
    referrIdx--;
}

// 라인 제거
function cancelLine(i, tag, name, func) {
    addLine = $(tag);
    let child = addLine.children();

    let changeEle = "";
    for (let j = 0; j < i; j++) {
        changeEle += '<li class="list-group-item">';
        changeEle += "<span>" + child[j].childNodes[0].innerText + "</span>";
        changeEle += child[j].childNodes[1].data;
        changeEle += '<input type="hidden" name="' + name + '" value="' + child[j].childNodes[2].value + '">';
        changeEle += '<button type="button" class="btn-close" aria-label="Close" onclick="' + func + '(' + j + ');"></button>';
        changeEle += '</li>';
    }
    for (let j = i +1; j < child.length; j++) {
        changeEle += '<li class="list-group-item">';
        changeEle += '<span>' + (parseInt(child[j].childNodes[0].innerText) -1) + '</span>';
        changeEle += child[j].childNodes[1].data;
        changeEle += '<input type="hidden" name="' + name + '" value="' + child[j].childNodes[2].value + '">';
        changeEle += '<button type="button" class="btn-close" aria-label="Close" onclick="' + func + '(' + (j -1) + ');"></button>';
        changeEle += '</li>';
    }
    addLine.html(changeEle);
}

function approversSendList(fk) {
    const APPORVER = 1;
    const  COLLABORATOR = 2;
    const REFERRER = 3;

    let approverList = [];
    let cnt = 1;

    if($("#approval-line").children().length < 1) {
        alert("결재자를 최소 1명 이상 지정해야 합니다.");
        return;
    }

    for (var approverEle of $("#approval-line").children()) {
        let idx = $(approverEle).children().eq(1).val();

        // memberList에서 특정 id 값을 가진 객체 찾기
        const member= memberList.find(i => i.id === parseInt(idx));
        approverList.push({
            'workFlowId': fk,
            'sequenceNum': cnt,
            'approverType': APPORVER,
            'employeeId': member.id,
            'approverEmail': member.email,
            'approverPhone': member.phone,
            'approverName': member.name,
            'approverRank': member.rank,
            'department': member.departmentName,
        });
        cnt++;
    }

    cnt = 1;
    for (var approverEle of $("#collaborator-line").children()) {
        let idx = $(approverEle).children().eq(1).val();

        const member = memberList.find(i => i.id === parseInt(idx));
        approverList.push({
            'workFlowId': fk,
            'sequenceNum': cnt,
            'approverType': COLLABORATOR,
            'employeeId': member.id,
            'approverEmail': member.email,
            'approverPhone': member.phone,
            'approverName': member.name,
            'approverRank': member.rank,
            'department': member.departmentName
        });
        cnt++;
    }

    cnt = 1;
    for (var approverEle of $("#referrer-line").children()) {
        let idx = $(approverEle).children().eq(1).val();

        const member = memberList.find(i => i.id === parseInt(idx));
        approverList.push({
            'workFlowId': fk,
            'sequenceNum': cnt,
            'approverType': REFERRER,
            'employeeId': member.id,
            'approverEmail': member.email,
            'approverPhone': member.phone,
            'approverName': member.name,
            'approverRank': member.rank,
            'department': member.departmentName
        });
        cnt++;
    }

    $.ajax({
        url: "/work-flow/approver-send",
        type: "post",
        data: JSON.stringify(approverList),
        processData: false,
        contentType: "application/json",
        dataType: 'json',
        success: function(response) {
            alert("결재 발송");
            window.location = response.url;
        },
        error: function(response) {
            console.log(response);
        }
    });

    // console.dir(approverList);
}