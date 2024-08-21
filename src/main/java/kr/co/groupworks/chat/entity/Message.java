package kr.co.groupworks.chat.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

// mongoDB Message
@Data
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatRoomId;
    private Long senderId;
    private String senderName;
    private String senderDepartment;
    private String senderRank;
    private String content;
    private LocalDateTime timestamp;
}