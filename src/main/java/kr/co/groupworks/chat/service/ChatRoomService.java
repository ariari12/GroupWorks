package kr.co.groupworks.chat.service;

import kr.co.groupworks.chat.dto.ChatRoomDTO;
import kr.co.groupworks.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    ChatRoom createChatRoom(ChatRoom chatRoom);      // 새로운 채팅방을 생성하는 메서드
    ChatRoom getChatRoomById(Long id);               // 특정 채팅방 ID로 채팅방을 가져오는 메서드
    List<ChatRoom> getChatRoomsForUser(Long userId); // 특정 사용자에 해당하는 채팅방 목록을 가져오는 메서드
}
