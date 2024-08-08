package kr.co.groupworks.notification.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {
    private String notificationId;
    private String title;
    private String contents;
    private String createdDate;
    private String type;
    private String url;
    private boolean read;
    private String senderName;
    private String receiverName;
}
