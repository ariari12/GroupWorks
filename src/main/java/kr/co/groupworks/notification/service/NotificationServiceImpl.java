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
                .receiverName(vacation.getEmployee().getEmployeeName())
                .build();
        NotificationDTO dto = notificationMapper.toDto(
                saveWithTTL(notification, 30L, TimeUnit.DAYS)
        );
        log.info(dto.toString());
        notificationSseEmitter.sendNotification(vacation.getEmployee().getEmployeeId(), dto);
    }

    @Cacheable(value = "notificationCache", key = "#receiverId")
    public List<NotificationDTO> getAllNotificationsByReceiverId(Long receiverId) {
        return notificationRepository.findAllByReceiverId(receiverId)
                .stream().map(notificationMapper::toDto)
                .toList();
    }
}
