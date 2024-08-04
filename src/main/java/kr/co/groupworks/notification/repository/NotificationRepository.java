package kr.co.groupworks.notification.repository;

import kr.co.groupworks.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
