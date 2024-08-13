document.addEventListener("DOMContentLoaded", function() {
    let cachedNotifications = null;

    // SSE 연결을 설정합니다. 서버에서 알림을 실시간으로 수신합니다.
    const eventSource = new EventSource('/notifications/sse');

    // 로컬 스토리지에서 배지 상태를 확인합니다.
    const notificationBadge = document.querySelector('.translate-middle.badge');
    if (localStorage.getItem('notificationBadgeVisible') === 'true') {
        // 배지가 보이는 상태라면, 숨김 클래스를 제거합니다.
        notificationBadge.classList.remove('d-none');
    }

    // SSE로 새로운 알림 수신
    eventSource.onmessage = function(event) {
        const notification = JSON.parse(event.data);
        console.log("Received notification: ", notification);

        // 새로운 알림을 캐시된 데이터에 추가
        if (cachedNotifications) {
            cachedNotifications.push(notification);
        }

        // 배지 보이기
        notificationBadge.classList.remove('d-none');
        localStorage.setItem('notificationBadgeVisible', 'true');

        // 새로운 알림을 화면에 표시
        showNotification(notification);
    };

    eventSource.onerror = function(err) {
        // SSE 연결이 실패한 경우 오류를 콘솔에 출력합니다.
        console.error("EventSource failed: ", err);
    };

    // 알림을 화면에 표시하는 함수
    function showNotification(notification) {
        const toast = document.createElement('div');
        toast.classList.add('toast');
        toast.setAttribute('role', 'alert');
        toast.setAttribute('aria-live', 'assertive');
        toast.setAttribute('aria-atomic', 'true');
        toast.setAttribute('data-bs-autohide', 'false');

        toast.innerHTML = `
            <div class="toast-header">
                <strong class="me-auto">
                    <a href="${notification.url}">🔔 ${notification.title}</a>
                </strong>
                <small>${notification.createdDate}</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                ${notification.contents}
            </div>
        `;

        const toastContainer = document.getElementById('toast-container');
        toastContainer.appendChild(toast);

        const toastBootstrap = new bootstrap.Toast(toast);
        toastBootstrap.show();
    }

    // 오프캔버스 열기 이벤트 리스너 추가
    const notificationButton = document.querySelector('[data-bs-target="#notificationOffcanvasRight"]');
    notificationButton.addEventListener('click', function() {
        if (!cachedNotifications) {
            // 서버에서 알림을 조회하고 캐싱
            fetch('/notifications/all')
                .then(response => response.json())
                .then(data => {
                    cachedNotifications = data;
                    displayNotifications(cachedNotifications);
                })
                .catch(error => console.error('Error fetching notifications:', error));
        } else {
            // 캐싱된 알림을 표시
            displayNotifications(cachedNotifications);
        }

        // 알림 배지 숨기기
        notificationBadge.classList.add('d-none');
        localStorage.setItem('notificationBadgeVisible', 'false');
    });

    // 오프캔버스에 알림을 표시하는 함수
    function displayNotifications(notifications) {
        const offcanvasBody = document.querySelector('#notificationOffcanvasRight .offcanvas-body');
        offcanvasBody.innerHTML = ''; // 기존 내용 지우기

        notifications.forEach(notification => {
            const notificationElement = document.createElement('div');
            notificationElement.classList.add('notification-item');
            notificationElement.innerHTML = `
                <div class="d-flex justify-content-between align-items-start">
                    <div>
                        <strong>
                            <a href="${notification.url}">${notification.title}</a>
                        </strong>
                        <p>${notification.contents}</p>
                        <small>${notification.createdDate}</small>
                    </div>
                    <button type="button" class="btn-close delete-notification" data-id="${notification.notificationId}" aria-label="Close"></button>
                </div>
                <hr>
            `;
            offcanvasBody.appendChild(notificationElement);
        });
    }
});