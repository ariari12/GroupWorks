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

    // 새로운 채팅방을 생성하는 메서드
    @PostMapping("/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        // ChatRoomDTO에서 ChatRoom 엔티티로 변환
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatRoomDTO.getName());
        chatRoom.setParticipants(chatRoomDTO.getParticipants());

        // 채팅방 생성
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoom);
        return ResponseEntity.ok(createdChatRoom);
    }

    // 특정 ID를 기반으로 채팅방을 가져오는 메서드
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable Long id) {
        ChatRoom chatRoom = chatRoomService.getChatRoomById(id);
        return ResponseEntity.ok(chatRoom);
    }

    // 사용자가 소속된 채팅방만 가져오는 메서드
    @GetMapping("/user/{userId}")
    public List<ChatRoom> getChatRoomsForUser(@PathVariable Long userId) {
        return chatRoomService.getChatRoomsForUser(userId);
    }


}
