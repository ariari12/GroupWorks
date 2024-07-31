package kr.co.groupworks.chat.entity;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;

@Entity
@Table(name = "chat_room_alias")
public class ChatRoomAlias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alias_id")
    private Long aliasId;

    private String alias;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
