package kr.co.groupworks.chat.controller;


import kr.co.groupworks.chat.dto.MessageDTO;
import kr.co.groupworks.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    // 메시지를 전송하는 엔드포인트.
    @PostMapping("/send")
    ResponseEntity<Void> sendMessage(@RequestBody MessageDTO messageDTO) {
        chatService.sendMessage(
                messageDTO.getChatRoomId(),
                messageDTO.getSenderId(),
                messageDTO.getContent()
        );
        return ResponseEntity.ok().build();
    }

    // 특정 채팅방의 메시지 목록을 가져오는 엔드포인트.
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> getMessages(@RequestParam String chatRoomId) {
        List<MessageDTO> messages = chatService.getMessages(chatRoomId).stream()
                .map(message -> new MessageDTO(
                        message.getChatRoomId(),
                        message.getSenderId(),
                        message.getSenderName(),
                        message.getSenderDepartment(),
                        message.getSenderRank(),
                        message.getContent(),
                        message.getTimestamp()
                        )
                )
                .toList();
        return ResponseEntity.ok(messages);
    }
}
