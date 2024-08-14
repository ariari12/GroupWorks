package kr.co.groupworks.chat.entity;


import jakarta.persistence.*;

import java.util.Set;

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
}
