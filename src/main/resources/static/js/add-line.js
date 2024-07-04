let approvIdx = 0;
let collabIdx = 0;
let referrIdx = 0;
let employeeNum;
let addLine;
let memberList = [];

// !임시 더미 데이터 추후 삭제 예정
for(let i = 1; i <= 50; i++) {
    memberList[i -1] = {
        id: i,
        name: "member" + i,
        dpptno: (i % 5) + "0" + (i % 3),
        dept: "members" + i,
        rank: "rank" + i,
    };
}

window.onload = () => {
    let searchForm = $("#search"); // 검색 입력
    let suggestion = $(".dropdown-menu"); // 추천 키워드

    searchForm.keyup(function (e) {
        suggestion.empty();

        for (const item of memberList) {
            if(item.name.indexOf(this.value) > -1 || item.dept.indexOf(this.value) > -1 || item.rank.indexOf(this.value) > -1) {
                suggestion.append(
                '<li class="dropdown-item" onclick="selectVal(this);" value="' + item.id +'"">'
                + "사원: " + item.name + " 부서: " + item.dept + ' 직급: ' + item.rank
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
    add += '<button type="button" class="btn-close" aria-label="Close" onclick="'
        + func + '(' + idx + ')">';
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
        changeEle += '<button type="button" class="btn-close" aria-label="Close" onclick="'
            + func + '(' + j + ');"></button>';
        changeEle += '</li>';
    }
    for (let j = i +1; j < child.length; j++) {
        changeEle += '<li class="list-group-item">';
        changeEle += '<span>' + (parseInt(child[j].childNodes[0].innerText) -1) + '</span>';
        changeEle += child[j].childNodes[1].data;
        changeEle += '<input type="hidden" name="' + name + '" value="' + child[j].childNodes[2].value + '">';
        changeEle += '<button type="button" class="btn-close" aria-label="Close" onclick="'
            + func + '(' + (j -1) + ');"></button>';
        changeEle += '</li>';
    }
    addLine.html(changeEle);
}
