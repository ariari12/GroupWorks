package kr.co.groupworks.notification.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 레디스 저장
@RedisHash("notification")
public class Notification implements Serializable {
    @Id
    private String notificationId;
    private String title;
    private String contents;
    private String createdDate;
    private String type;
    private String url;
    private boolean isRead;
    private Long receiverId;
    private Long senderId;
    private String senderName;
    private String receiverName;

}
