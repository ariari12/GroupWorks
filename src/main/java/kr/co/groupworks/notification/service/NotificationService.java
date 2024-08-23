package kr.co.groupworks.notification.service;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.notification.dto.NotificationDTO;
import kr.co.groupworks.notification.model.Notification;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface NotificationService {
    Notification saveWithTTL(Notification notification, Long ttl, TimeUnit timeUnit);
    void sendVacationApproval(Vacation vacation, Employee employee);
    List<NotificationDTO> getAllNotificationsByReceiverId(Long employeeId);
    void sendNotificationOne(Notification notification);
    void sendNotificationList(List<Notification> notificationList);

    void deleteAllNotificationsByReceiverId(Long employeeId);

    void deleteNotificationById(String notificationId, Long employeeId);

    void evictVacationCache(Long employeeId);
}
