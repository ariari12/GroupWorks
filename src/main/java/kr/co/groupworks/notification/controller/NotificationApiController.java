package kr.co.groupworks.notification;

import io.swagger.v3.oas.annotations.Hidden;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.notification.dto.NotificationDTO;
import kr.co.groupworks.notification.service.NotificationService;
import kr.co.groupworks.notification.sse.NotificationSseEmitter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Slf4j
@Hidden
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationApiController {

    private final NotificationSseEmitter notificationSseEmitter;
    private final NotificationService notificationService;

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {
//        log.info("NotificationController");
        return notificationSseEmitter.connect(sessionEmployeeDTO.getEmployeeId());
    }
    // 전체 알림 조회
    @GetMapping("/all")
    public List<NotificationDTO> getAllNotifications(@SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {
//        log.info("NotificationController - Fetching All Notifications");
        return notificationService.getAllNotificationsByReceiverId(sessionEmployeeDTO.getEmployeeId());

    }
    // 전체 알림 삭제
    @DeleteMapping("/deleteAll")
    public void deleteAllNotifications(@SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("NotificationController - Deleting All Notifications");
        notificationService.deleteAllNotificationsByReceiverId(sessionEmployeeDTO.getEmployeeId());
    }

    // 개별 알림 삭제
    @DeleteMapping("/deleteOne/{notificationId}")
    public void deleteOneNotification(@PathVariable String notificationId, @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("NotificationController - Deleting One Notification with ID: {}", notificationId);
        notificationService.deleteNotificationById(notificationId, sessionEmployeeDTO.getEmployeeId());
    }


}