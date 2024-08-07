package kr.co.groupworks.notification.sse;

import kr.co.groupworks.notification.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class NotificationSseEmitter {
    private final Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    public SseEmitter connect(Long employeeId) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitters.put(employeeId, sseEmitter);
        sseEmitter.onCompletion(() -> sseEmitters.remove(employeeId));
        sseEmitter.onTimeout(() -> sseEmitters.remove(employeeId));
        log.info("NotificationSseEmitter employee {}", employeeId);
        return sseEmitter;
    }

    public void sendNotification(Long employeeId, Notification notification) {
        SseEmitter sseEmitter = sseEmitters.get(employeeId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(notification);
            } catch (IOException e) {
                sseEmitters.remove(employeeId);
            }
        }
    }
}
