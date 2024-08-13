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

        // 개별 삭제 버튼 이벤트 리스너 추가
        const deleteButton = toast.querySelector('.delete-notification');
        deleteButton.addEventListener('click', function() {
            const notificationId = this.getAttribute('data-id');
            deleteNotification(notificationId, toast);
        });
    }

    // 개별 알림 삭제 함수
    function deleteNotification(notificationId, notificationElement) {
        fetch(`/notifications/deleteOne/${notificationId}`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    notificationElement.remove(); // 화면에서 알림 제거
                    cachedNotifications = cachedNotifications.filter(n => n.notificationId !== notificationId); // 캐시에서도 제거
                } else {
                    console.error('Failed to delete notification');
                }
            })
            .catch(error => console.error('Error deleting notification:', error));
    }

    // 오프캔버스 열기 이벤트 리스너 추가
    const notificationButton = document.querySelector('[data-bs-target="#notificationOffcanvasRight"]');
    notificationButton.addEventListener('click', function() {
        // 서버에서 알림을 조회하고 화면에 표시
        fetch('/notifications/all')
            .then(response => response.json())
            .then(data => {
                displayNotifications(data);
            })
            .catch(error => console.error('Error fetching notifications:', error));

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

            // 개별 삭제 버튼 이벤트 리스너 추가
            notificationElement.querySelector('.delete-notification').addEventListener('click', function() {
                const notificationId = this.getAttribute('data-id');
                deleteNotification(notificationId, notificationElement);
            });
        });
    }

    // 알림 전체 삭제 버튼 클릭 이벤트 리스너 추가
    const deleteAllButton = document.querySelector('.btn-outline-danger');
    deleteAllButton.addEventListener('click', function() {
        deleteAllNotifications();
    });

    // 알림 전체 삭제 함수
    function deleteAllNotifications() {
        fetch(`/notifications/deleteAll`, { method: 'DELETE' })
            .then(response => {
                if (response.ok) {
                    const offcanvasBody = document.querySelector('#notificationOffcanvasRight .offcanvas-body');
                    offcanvasBody.innerHTML = ''; // 화면에서 모든 알림 제거
                    cachedNotifications = []; // 캐시 초기화
                } else {
                    console.error('Failed to delete all notifications');
                }
            })
            .catch(error => console.error('Error deleting all notifications:', error));
    }
});