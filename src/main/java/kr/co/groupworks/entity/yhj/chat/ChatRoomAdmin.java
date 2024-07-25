package kr.co.groupworks.entity.yhj.chat;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;

@Entity
@Table(name = "chat_room_admin")
public class ChatRoomAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
