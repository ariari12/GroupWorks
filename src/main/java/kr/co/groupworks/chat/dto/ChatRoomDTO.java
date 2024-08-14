package kr.co.groupworks.chat.dto;


import lombok.*;

import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDTO {

    private Long chatRoomId;
    private String name;
    private Set<Long> participants;
}
