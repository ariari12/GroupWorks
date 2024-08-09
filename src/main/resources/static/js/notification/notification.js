document.addEventListener("DOMContentLoaded", function() {
    const eventSource = new EventSource(`/notifications/sse`);

    // Check local storage for the badge state
    const notificationBadge = document.querySelector('.translate-middle.badge');
    if (localStorage.getItem('notificationBadgeVisible') === 'true') {
        notificationBadge.classList.remove('d-none');
    }

    eventSource.onmessage = function(event) {
        const notification = JSON.parse(event.data);
        console.log("Received notification: ", notification);

        // Create a new toast element
        const toast = document.createElement('div');
        toast.classList.add('toast');
        toast.setAttribute('role', 'alert');
        toast.setAttribute('aria-live', 'assertive');
        toast.setAttribute('aria-atomic', 'true');
        toast.setAttribute('data-bs-autohide', 'false');

        toast.innerHTML = `
                    <div class="toast-header">
                       
                        <strong class="me-auto">
                        <a href="${notification.url}">🔔 ${notification.title}
                        </a></strong>
                        <small>${notification.createdDate}</small>
                        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                    </div>
                    <div class="toast-body">
                        ${notification.contents}
                    </div>
                `;

        // Append the toast to the container
        const toastContainer = document.getElementById('toast-container');
        toastContainer.appendChild(toast);

        // Initialize the toast using Bootstrap's toast function
        const toastBootstrap = new bootstrap.Toast(toast);
        toastBootstrap.show();

        // 알림 배지 추가
        notificationBadge.classList.remove('d-none');

        // Save badge state to local storage
        localStorage.setItem('notificationBadgeVisible', 'true');
    };

    eventSource.onerror = function(err) {
        console.error("EventSource failed: ", err);
    };
    // 알림 배지 클릭시 제거
    const notificationButton = document.querySelector('[data-bs-target="#notificationOffcanvasRight"]');
    notificationButton.addEventListener('click', function() {
        const notificationBadge = document.querySelector('.translate-middle.badge');
        notificationBadge.classList.add('d-none');
        // Save badge state to local storage
        localStorage.setItem('notificationBadgeVisible', 'false');
    });
});