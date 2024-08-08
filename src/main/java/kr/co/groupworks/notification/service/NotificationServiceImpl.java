package kr.co.groupworks.notification.service;

import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.repository.NotificationRepository;
import kr.co.groupworks.notification.sse.NotificationSseEmitter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationRepository notificationRepository;
    private final NotificationSseEmitter notificationSseEmitter;

    public void saveWithTTL(Notification notification, Long timeout) {
        notificationRepository.saveWithTTL(notification, timeout, TimeUnit.DAYS);
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
        log.info(notification.getNotificationId());
        saveWithTTL(notification,30L);
        notificationSseEmitter.sendNotification(notification.getReceiverId(), notification);
    }
}
