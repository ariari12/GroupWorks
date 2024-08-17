package kr.co.groupworks.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {

    private String chatRoomId;          // 방번호
    private Long senderId;              // 보낸 사람 ID
    private String senderName;          // 보낸 사람 이름
    private String senderDepartment;    // 보낸 사람 부서
    private String senderRank;          // 보낸 사람 직급
    private String content;             // 보낸 내용
    private LocalDateTime timestamp;    // 보낸 시간
}
