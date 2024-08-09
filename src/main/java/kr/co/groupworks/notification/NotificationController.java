package kr.co.groupworks.notification;

import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.notification.sse.NotificationSseEmitter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationSseEmitter notificationSseEmitter;

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("NotificationController");
        return notificationSseEmitter.connect(sessionEmployeeDTO.getEmployeeId());
    }
}