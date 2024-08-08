package kr.co.groupworks.notification.service;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.notification.model.Notification;

public interface NotificationService {
    void saveWithTTL(Notification notification, Long ttl);
    void sendVacationApproval(Vacation vacation, Employee employee);

}
