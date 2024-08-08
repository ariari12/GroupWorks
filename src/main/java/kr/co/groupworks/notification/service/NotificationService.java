package kr.co.groupworks.notification.service;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.notification.model.Notification;

import java.util.concurrent.TimeUnit;

public interface NotificationService {
    Notification saveWithTTL(Notification notification, Long ttl, TimeUnit timeUnit);
    void sendVacationApproval(Vacation vacation, Employee employee);

}
