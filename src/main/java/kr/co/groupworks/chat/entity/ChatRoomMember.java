package kr.co.groupworks.chat.entity;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room_member")
public class ChatRoomMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "join_at")
    private LocalDateTime joinedAt;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
