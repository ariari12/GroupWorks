package kr.co.groupworks.chat.controller;

import kr.co.groupworks.chat.dto.MessageDTO;
import kr.co.groupworks.chat.entity.Message;
import kr.co.groupworks.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class WebSocketMessageController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(MessageDTO dto) {
        // 메시지를 MongoDB에 저장
        Message msg = chatService.sendMessage(
                dto.getChatRoomId(),
                dto.getSenderId(),
                dto.getContent()
        );

        // 메시지를 특정 방으로 전송
        messagingTemplate.convertAndSend("/topic/rooms/" + dto.getChatRoomId(), msg);
    }

}
