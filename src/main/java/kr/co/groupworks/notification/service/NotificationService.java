package kr.co.groupworks.notification.service;

import kr.co.groupworks.notification.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getNotifications();
}
