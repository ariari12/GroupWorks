package kr.co.groupworks.notification.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("notice")
public class NotificationDTO implements Serializable {
    private Long notificationId;
    // 제목
    private String title;
    // 내용
    private String contents;
    // 알람 생성일
    private String createdDate;
    // 알람 종류
    private String type;
    // 알람 링크 눌렀을 경우 페이지 이동
    private String url;
    // 알람 확인 여부
    private boolean isRead;

    // 수신자 이름
    private String receiver;
    // 발신자 이름
    private String sender;
}
