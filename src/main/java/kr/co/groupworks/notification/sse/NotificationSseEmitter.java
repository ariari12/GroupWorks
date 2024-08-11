package kr.co.groupworks.notification.sse;

import kr.co.groupworks.notification.dto.NotificationDTO;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSseEmitter {
    private final Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    // 사용자 연결 처리
    public SseEmitter connect(Long employeeId) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitters.put(employeeId, sseEmitter);

        // 연결 완료 시 사용자 제거
        sseEmitter.onCompletion(() -> sseEmitters.remove(employeeId));

        // 연결 시간 초과 시 사용자 제거
        sseEmitter.onTimeout(() -> sseEmitters.remove(employeeId));

        log.info("NotificationSseEmitter employee {}", employeeId);
        return sseEmitter;
    }

    // 알림 전송
    public void sendNotification(Long receiverId, NotificationDTO notificationDTO) {
        SseEmitter sseEmitter = sseEmitters.get(receiverId);

        // SSE 연결이 있는 경우에만 알림 전송
        if (sseEmitter != null) {
            try {
                sseEmitter.send(notificationDTO);
            } catch (IOException e) {
                sseEmitters.remove(receiverId);
            }
        }
    }
}
