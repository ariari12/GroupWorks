document.addEventListener("DOMContentLoaded", function() {
    const eventSource = new EventSource(`/notifications/sse`);
    eventSource.onmessage = function(event) {
        const notification = JSON.parse(event.data);
        console.log("Received notification: ", notification);
        // 알림을 UI에 표시하는 로직 추가

    };

    eventSource.onerror = function(err) {
        console.error("EventSource failed: ", err);
    };
});