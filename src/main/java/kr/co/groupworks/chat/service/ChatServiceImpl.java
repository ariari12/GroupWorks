package kr.co.groupworks.chat.service;


import kr.co.groupworks.chat.dto.MessageDTO;
import kr.co.groupworks.chat.entity.Message;
import kr.co.groupworks.chat.repository.MessageRepository;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final MessageRepository messageRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Message sendMessage(String chatRoomId, Long senderId, String content) {

        var employee = employeeRepository.findById(senderId).orElse(null);

        Message message = new Message();
        message.setChatRoomId(chatRoomId);
        message.setSenderId(senderId);
        message.setSenderName(employee.getEmployeeName());
        message.setSenderDepartment(employee.getDepartment().getDepartmentName());
        message.setSenderRank(employee.getRankName());
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }

    @Override
    public List<MessageDTO> getMessages(String chatRoomId) {
        return messageRepository.findByChatRoomId(chatRoomId).stream()
                .map(message -> new MessageDTO(
                        message.getChatRoomId(),
                        message.getSenderId(),
                        message.getSenderName(),
                        message.getSenderDepartment(),
                        message.getSenderRank(),
                        message.getContent(),
                        message.getTimestamp()
                ))
                .toList();
    }
}
