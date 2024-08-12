/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {
    sideBar();
    sideMenu('pagesCollapseActive');
    sideMenu('commutePages');
    sideMenu('boardPages');
    sideMenu('vacationPages');
    sideMenu('mailPages');
    sideMenu('pagesCollapseActive');
    sideMenu('videoChattingPage');

    sideMenu('materialFlowMenu');
});


function sideBar() {
    // Toggle the side navigation 사이드 메뉴 토글기능
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // 새로 고침 사이에 사이드바 토글을 유지
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
}

function sideMenu(id) {
    const collapseElement = document.getElementById(id);
    const triggerElement = document.querySelector('[data-bs-toggle="collapse"][data-bs-target="#' + id + '"]');

    // Local Storage 에서 축소 상태를 확인
    if (localStorage.getItem(id) === 'true') {
        collapseElement.classList.add('show');
        triggerElement.classList.remove('collapsed');
        triggerElement.setAttribute('aria-expanded', 'true');
    } else {
        collapseElement.classList.remove('show');
        triggerElement.classList.add('collapsed');
        triggerElement.setAttribute('aria-expanded', 'false');
    }

    // 접힌 상태가 변경될 때 Local Storage 업데이트하는 이벤트 리스너를 추가
    collapseElement.addEventListener('shown.bs.collapse', () => {
        localStorage.setItem(id, 'true');
    });

    collapseElement.addEventListener('hidden.bs.collapse', () => {
        localStorage.setItem(id, 'false');
    });
}