package kr.co.groupworks.chat.entity;

import jakarta.persistence.*;
import kr.co.groupworks.employee.entity.Employee;

@Entity
@Table(name = "chat_room_admin")
public class ChatRoomAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
