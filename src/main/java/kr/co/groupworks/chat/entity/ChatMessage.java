package kr.co.groupworks.chat.entity;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    private String content;

    @Column(name = "send_at")
    private LocalDateTime sentAt;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee sender;
}
