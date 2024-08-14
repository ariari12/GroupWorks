package kr.co.groupworks.chat.service;

import kr.co.groupworks.chat.dto.ChatRoomDTO;
import kr.co.groupworks.chat.entity.ChatRoom;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoom> getAllChatRooms();
    ChatRoom createChatRoom(ChatRoom chatRoom);
    ChatRoom getChatRoomById(Long id);
    List<ChatRoom> getChatRoomsForUser(Long userId);
}
