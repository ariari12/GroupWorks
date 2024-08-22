package kr.co.groupworks.chat.service;

import kr.co.groupworks.chat.entity.Message;

import java.util.List;

public interface MessageService {
    void saveMessage(Message message);
    List<Message> getMessagesByChatRoomId(String chatRoomId);
}
