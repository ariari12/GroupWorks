package kr.co.groupworks.notification.service;

import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.common.mapper.NotificationMapper;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.notification.dto.NotificationDTO;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.repository.NotificationRepository;
import kr.co.groupworks.notification.sse.NotificationSseEmitter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationSseEmitter notificationSseEmitter;
    private final NotificationMapper notificationMapper;

    public Notification saveWithTTL(Notification notification, Long timeout, TimeUnit timeUnit) {
        return notificationRepository.saveWithTTL(notification, timeout, timeUnit);
    }

    //수신자의 모든 알림 캐싱
    @Cacheable(value = "notificationCache", key = "#receiverId")
    public List<NotificationDTO> getAllNotificationsByReceiverId(Long receiverId) {
        return notificationRepository.findAllByReceiverId(receiverId)
                .stream().map(notificationMapper::toDto)
                .toList();
    }

    // 캐싱 제거, 레디스 저장 후 전송
    @CacheEvict(value = "notificationCache", key = "#vacation.employee.employeeId")
    public void sendVacationApproval(Vacation vacation, Employee sender) {
        Notification notification = Notification.builder()
                .title("휴가 신청 알림")
                .url("/vacation")
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .read(false)
                .contents("휴가 신청이 " + vacation.getStatus().getDescription() + "되었습니다.")
                .receiverId(vacation.getEmployee().getEmployeeId())
                .senderId(sender.getEmployeeId())
                .senderName(sender.getEmployeeName())
                .build();
        NotificationDTO dto = notificationMapper.toDto(
                saveWithTTL(notification, 30L, TimeUnit.DAYS)
        );
        log.info(dto.toString());
        notificationSseEmitter.sendNotification(vacation.getEmployee().getEmployeeId(), dto);
    }


    // 캐싱 제거, 레디스 저장 후 한명에게 전송
    @CacheEvict(value = "notificationCache", key = "#notification.senderId")
    public void sendNotificationOne(Notification notification) {
        NotificationDTO dto = notificationMapper.toDto(
                saveWithTTL(notification, 30L, TimeUnit.DAYS)
        );
        log.info("sendNotificationOne - dto : {}",dto.toString());
        notificationSseEmitter.sendNotification(Long.valueOf(dto.getNotificationId()), dto);
    }

    // 캐싱 제거, 레디스 저장 후 여러명에게 전송
    @CacheEvict(value = "notificationCache", allEntries = true)
    public void sendNotificationList(List<Notification> notificationList) {
        List<NotificationDTO> list = notificationList.stream().map(
                notification -> notificationMapper.toDto(saveWithTTL(notification, 30L, TimeUnit.DAYS))
        ).toList();
        log.info("sendNotificationList - list : {}",list);
        notificationSseEmitter.sendNotificationsToMultipleUsers(list);
    }
}
