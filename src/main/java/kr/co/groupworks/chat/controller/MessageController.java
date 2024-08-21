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

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String chatRoomId) {
        List<Message> messages = messageService.getMessagesByChatRoomId(chatRoomId);
        return ResponseEntity.ok(messages);
    }


}
