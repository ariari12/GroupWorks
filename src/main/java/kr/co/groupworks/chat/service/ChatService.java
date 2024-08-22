package kr.co.groupworks.chat.service;

import kr.co.groupworks.chat.dto.MessageDTO;
import kr.co.groupworks.chat.entity.Message;

import java.util.List;

public interface ChatService {
    Message sendMessage(String chatRoomId, Long senderId, String content);
    List<MessageDTO> getMessages(String chatRoomId);
}
