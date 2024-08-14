package kr.co.groupworks.workflow.service;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.service.NotificationService;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;
import kr.co.groupworks.workflow.repository.WorkFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkflowNotificationServiceFactory {
    private final NotificationService service;
    private final EmployeeRepository er;
    private final WorkFlowRepository wr;

    public void notifySetup(WorkFlowDTO wfDTO, String title, String content) {
        service.sendNotificationList(wfDTO.getApprovers().stream()
                .map(a -> Notification.builder()
                        .title(title)
                        .contents(content)
                        .type("Workflow-전자결재 요청")
                        .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .receiverId(a.getEmployeeId())
                        .senderId(wfDTO.getEmployeeId())
                        .senderName(wfDTO.getEmployeeName())
                        .url("/work-flow/stat")
                        .build())
                .toList());
    }

    public Map<String, Object> notifySetup(Long wid, Long eId, Integer aType, String title, NotifyContent content) {
        WorkFlowEntity wf = wr.findById(wid).orElse(null);
        if(wf == null) return returnMessage("not found workflowId: " + wid, false);

        Employee e = er.findById(eId).orElse(null);
        if(e == null) return returnMessage("not found employeeId: " + eId, false);

        List<Notification> notifyList = new ArrayList<>();
        wf.getApprovers().forEach(a -> notifyList.add(Notification.builder()
                .title(title)
                .contents(content.getContent(wf, e))
                .type("Workflow-전자결재")
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .receiverId(a.getEmployeeId())
                .senderId(e.getEmployeeId())
                .senderName(e.getEmployeeName())
                .url("/work-flow/stat")
                .build()
        ));
        notifyList.add(Notification.builder()
                .title(title)
                .contents(content.getContent(wf, e))
                .type("Workflow-전자결재")
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .receiverId(wf.getEmployeeId())
                .senderId(e.getEmployeeId())
                .senderName(e.getEmployeeName())
                .url("/work-flow/stat")
                .build());
        service.sendNotificationList(notifyList);
        return null;
    }

    private Map<String, Object> returnMessage(String message, boolean result) {
        return Map.of("message", message, "result", result);
    }
}
