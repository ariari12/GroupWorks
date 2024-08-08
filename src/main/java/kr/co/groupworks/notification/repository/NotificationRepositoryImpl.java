package kr.co.groupworks.notification.repository;

import kr.co.groupworks.notification.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRedis{
    private final RedisTemplate<String, Notification> redisTemplate;

    @Override
    public void saveWithTTL(Notification notification, long timeout, TimeUnit unit) {
        String key = "notification:" + notification.getNotificationId();
        HashOperations<String, String, Notification> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, notification.getNotificationId(), notification);
        // TTL 설정
        redisTemplate.expire(key, timeout, unit);
    }
}