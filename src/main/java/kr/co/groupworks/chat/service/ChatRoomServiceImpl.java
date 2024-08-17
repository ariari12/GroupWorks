package kr.co.groupworks.chat.service;

import kr.co.groupworks.chat.entity.ChatRoom;
import kr.co.groupworks.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    // 새로운 채팅방을 생성하고 데이터베이스에 저장.
    @Override
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    // 주어진 ID로 채팅방을 조회.
    @Override
    public ChatRoom getChatRoomById(Long id) {
        return chatRoomRepository.findById(id).orElse(null);
    }

    // 주어진 사용자 ID를 포함하는 채팅방 목록을 조회하여 반환.
    @Override
    public List<ChatRoom> getChatRoomsForUser(Long userId) {
        return chatRoomRepository.findByParticipantsContaining(userId);
    }

}
