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
    workflowSideMenu();
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

/* 이 함수를 복사하여 각자 SideMuen function 정의 */
function workflowSideMenu() {
                                                                        // SideMenu Id
    const collapseElement = document.getElementById('pagesCollapseActive');                  // SideMenu Id
    const triggerElement = document.querySelector('[data-bs-toggle="collapse"][data-bs-target="#pagesCollapseActive"]');

    // Local Storage 에서 축소 상태를 확인
    if (localStorage.getItem('pagesCollapseActive') === 'true') {
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
        localStorage.setItem('pagesCollapseActive', 'true');
    });

    collapseElement.addEventListener('hidden.bs.collapse', () => {
        localStorage.setItem('pagesCollapseActive', 'false');
    });
}