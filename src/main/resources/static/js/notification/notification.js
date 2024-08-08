document.addEventListener("DOMContentLoaded", function() {
    const eventSource = new EventSource(`/notifications/sse`);
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
                       
                        <strong class="me-auto">🔔 ${notification.title}</strong>
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
    };

    eventSource.onerror = function(err) {
        console.error("EventSource failed: ", err);
    };
});