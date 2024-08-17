package kr.co.groupworks.chat.controller;

import kr.co.groupworks.chat.entity.Message;
import kr.co.groupworks.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    // 메시지를 전송하는 엔드포인트.
    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
        return ResponseEntity.ok(message);
    }

    // 특정 채팅방의 메시지 목록을 가져오는 엔드포인트.
    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String chatRoomId) {
        List<Message> messages = messageService.getMessagesByChatRoomId(chatRoomId);
        return ResponseEntity.ok(messages);
    }
}
