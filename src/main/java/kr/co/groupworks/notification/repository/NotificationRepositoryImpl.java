package kr.co.groupworks.notification.repository;

import kr.co.groupworks.notification.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRedis{
    private final RedisTemplate<String, Notification> redisTemplate;

    @Override
    public Notification saveWithTTL(Notification notification, long timeout, TimeUnit unit) {
        String key = "notification:" + notification.getNotificationId();
        HashOperations<String, String, Notification> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, notification.getNotificationId(), notification);
        // TTL 설정
        redisTemplate.expire(key, timeout, unit);
        // 저장된 데이터를 가져와 반환
        return hashOperations.get(key, notification.getNotificationId());
    }

    @Override
    public List<Notification> findAllByReceiverId(Long receiverId) {
        // Redis에서 'notification:*' 패턴에 해당하는 모든 키를 가져옵니다.
        Set<String> keys = redisTemplate.keys("notification:*");
        // Redis 해시(Hash) 작업을 수행하기 위해 HashOperations 객체를 생성합니다.
        HashOperations<String, String, Notification> hashOperations = redisTemplate.opsForHash();
        // receiverId와 일치하는 알림들을 저장할 리스트를 초기화합니다.
        List<Notification> notifications = new ArrayList<>();
        // Redis에서 가져온 키들이 null이 아닌지 확인합니다.
        if (keys != null) {
            // 각 키에 대해 반복합니다.
            for (String key : keys) {
                // 해당 키에 있는 모든 해시 엔트리를 가져옵니다.
                Map<String, Notification> entries = hashOperations.entries(key);

                // 각 엔트리의 값(Notification 객체)을 검사합니다.
                for (Notification notification : entries.values()) {
                    // notification의 receiverId가 주어진 receiverId와 일치하는지 확인합니다.
                    if (notification.getReceiverId().equals(receiverId)) {
                        // 일치하는 경우, notifications 리스트에 추가합니다.
                        notifications.add(notification);
                    }
                }
            }
        }
        // 최종적으로 일치하는 모든 알림이 담긴 리스트를 반환합니다.
        return notifications;
    }

}