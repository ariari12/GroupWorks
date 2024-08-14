package kr.co.groupworks.chat.controller;

import kr.co.groupworks.chat.dto.ChatRoomDTO;
import kr.co.groupworks.chat.entity.ChatRoom;
import kr.co.groupworks.chat.service.ChatRoomService;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom) {
        ChatRoom createChatRoom = chatRoomService.createChatRoom(chatRoom);
        return ResponseEntity.ok(createChatRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable Long id) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(id);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/user/{userId}")
    public List<ChatRoom> getChatRoomsForUser(@PathVariable Long userId) {
        return chatRoomService.getChatRoomsForUser(userId);
    }

}
