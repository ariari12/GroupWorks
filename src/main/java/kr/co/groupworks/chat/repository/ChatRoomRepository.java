package kr.co.groupworks.chat.repository;

import kr.co.groupworks.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    // participantId를 포함하는 채팅방 목록 조회.
    List<ChatRoom> findByParticipantsContaining(Long participantId);
}
