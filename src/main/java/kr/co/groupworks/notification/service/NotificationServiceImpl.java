package kr.co.groupworks.notification.service;

import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.sse.NotificationSseEmitter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final NotificationSseEmitter notificationSseEmitter;

    public void saveNotification(Notification notification) {
        String key = "notifications:" + notification.getReceiverId();
        redisTemplate.opsForList().leftPush(key, notification);
    }

    public void sendVacationApproval(Vacation vacation, Employee sender) {
        Notification notification = Notification.builder()
                .title("휴가 신청 알림")
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isRead(false)
                .contents("휴가 신청이 " + vacation.getStatus().getDescription() + "되었습니다.")
                .receiverId(vacation.getEmployee().getEmployeeId())
                .senderId(sender.getEmployeeId())
                .senderName(sender.getEmployeeName())
                .receiverName(vacation.getEmployee().getEmployeeName())
                .build();
        saveNotification(notification);
        notificationSseEmitter.sendNotification(notification.getReceiverId(), notification);
    }
}
