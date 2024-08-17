package kr.co.groupworks.chat.repository;

import kr.co.groupworks.chat.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    // chatRoomId를 사용하여 해당 채팅방에 속한 메시지들을 조회
    List<Message> findByChatRoomId(String chatRoomId);
}
