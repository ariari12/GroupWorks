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

    private String chatRoomId;
    private Long senderId;
    private String senderName;
    private String senderDepartment;
    private String senderRank;
    private String content;
    private LocalDateTime timestamp;
}
