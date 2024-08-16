package kr.co.groupworks.notification.dto;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;


@Data
@Hidden
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {
    // 알림 고유 번호
    private String notificationId;
    // 알림 제목
    private String title;
    // 알림 내용
    private String contents;
    // 알림 생성일
    private String createdDate;
    // 알림 이동할 페이지
    private String url;
    // 알림 읽음 여부
    private boolean read;
    // 수신자 고유번호
    private Long receiverId;
    // 수신자 이름
    private String receiverName;
    // 발신자 이름
    private String senderName;
}
