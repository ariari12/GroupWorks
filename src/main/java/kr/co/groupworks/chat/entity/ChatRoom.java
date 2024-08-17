package kr.co.groupworks.chat.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "chat_room_participants",
            joinColumns = @JoinColumn(name = "chat_room_id"))
    @Column(name = "participant_id")
    private Set<Long> participants;


    // 새로운 참가자를 채팅방에 추가.
    public ChatRoom addParticipant(Long participantId) {
        this.participants.add(participantId);
        return this;
    }
}
