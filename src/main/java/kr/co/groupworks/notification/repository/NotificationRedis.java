package kr.co.groupworks.notification.repository;

import kr.co.groupworks.notification.model.Notification;

import java.util.concurrent.TimeUnit;

public interface NotificationRedis {
    void saveWithTTL(Notification notification, long timeout, TimeUnit unit);
}
