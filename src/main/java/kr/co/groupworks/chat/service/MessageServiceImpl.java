package kr.co.groupworks.chat.service;


import kr.co.groupworks.chat.entity.Message;
import kr.co.groupworks.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    // 주어진 Message 객체를 데이터베이스에 저장.
    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    // 주어진 chatRoomId를 사용하여 해당 채팅방의 모든 메시지를 조회.
    @Override
    public List<Message> getMessagesByChatRoomId(String chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId);
    }
}
