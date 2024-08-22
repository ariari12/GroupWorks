package kr.co.groupworks.chat.dto;


import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDTO {

    private Long chatRoomId;        // 방번호
    private String name;            // 이름
    private Set<Long> participants; // 참여자 리스트
}
