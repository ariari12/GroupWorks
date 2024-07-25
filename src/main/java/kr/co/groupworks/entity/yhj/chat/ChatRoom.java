package kr.co.groupworks.entity.yhj.chat;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Department;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(nullable = false, name = "room_name")
    private String roomName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "room_type")
    private ChatRoomType roomType;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt  = LocalDateTime.now();

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;

    // @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "chatRoom")
    private Set<ChatRoomMember> members;

    // @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "chatRoom")
    private Set<ChatRoomAlias> aliases;

    // @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "chatRoom")
    private Set<ChatMessage> messages;

    // @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "chatRoom")
    private Set<ChatRoomAdmin> admins;

}

// 채팅방 타입 ( 1:1 / 부서 / 협업 )
enum ChatRoomType {
    ONE_TO_ONE,
    DEPARTMENT,
    COLLABORATION
}