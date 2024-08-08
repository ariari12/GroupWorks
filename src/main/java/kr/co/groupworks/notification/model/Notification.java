package kr.co.groupworks.notification.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
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
    private String type;
    private String url;
    private boolean read;
    private Long receiverId;
    private Long senderId;
    private String senderName;
    private String receiverName;

}
