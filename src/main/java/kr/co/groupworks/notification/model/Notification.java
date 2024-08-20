package kr.co.groupworks.notification.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 레디스 저장
@RedisHash("notification")
public class Notification implements Serializable {
    @Id
    @Builder.Default
    private String notificationId = UUID.randomUUID().toString();
    private String title;
    private String contents;
    private String createdDate;
    private String url; // 페이지
    private boolean read; //true 가 읽음
    private Long receiverId; //필수 값
    private Long senderId;
    private String senderName;
}
