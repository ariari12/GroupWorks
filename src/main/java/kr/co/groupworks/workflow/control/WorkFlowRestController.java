package kr.co.groupworks.workflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import kr.co.groupworks.workflow.service.WorkFlowService;
import kr.co.groupworks.workflow.service.WorkflowNotificationServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Hidden
@RequiredArgsConstructor
public class WorkFlowRestController {
    private final WorkFlowService workFlowService;
    private final WorkflowNotificationServiceFactory notifyService;

    private static final String WORKFLOW_URL = "work-flow";
    private static final String APPROVAL_HISTORY = "/approval-history";
    private static final String EMPLOYEE = "employee";
    private static final String FILE_SEND = "/file-send";
    private static final String FILE_RECEIVE = "/file-download";
    private static final String APPROVAL_REQUEST = "/request";
    private static final String APPROVAL = "/approval";
    private static final String APPROVAL_PARAM = "/{workFlowId}/{employeeId}/{approverType}";
    private static final String APPROVER_SEND = "/approver-send";
    private static final String WORK_STATUS = "/stat";
    private static final String SEPARATOR = "/";

    private String title;

    /* WorkFlow Request Receive 결재 요청 받기 */
    @PostMapping(SEPARATOR + WORKFLOW_URL + APPROVAL_REQUEST)
    public ResponseEntity<Map<String, Object>> requestOk(@Valid WorkFlowDTO workFlowDTO, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        title = "Approval Request";
//        log.info("WorkFlowRestController - request ok, workFlowDTO: {}", workFlowDTO);

        if (bindingResult.hasErrors()) {
            // 필수 입력항목의 유효성 결과 실패 시
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("WorkFlowRestController - request Fail! Error: {}", fieldError);
            }
            response.put("valid", false);
            response.put("errors", bindingResult.getAllErrors());
        }
        else {
            // 데이터 저장 로직
            long id = workFlowService.setWorkFlowDTO(workFlowDTO);
            response.put("valid", true);
            response.put("primaryKey", id);
        }
        return ResponseEntity.ok().body(response);
    }

    /* Attachment File Receive 결재 요청 첨부파일 받기 */
    @PostMapping(SEPARATOR + WORKFLOW_URL + FILE_SEND)
    public ResponseEntity<Map<String, String>> fileSendTest(@RequestParam("attach_file") MultipartFile[] files, @RequestParam("pk") long pk, HttpSession session) {
//        log.info("WorkFlowRestController - file-receive ok, files: {}", files.length);
//        for (MultipartFile file : files) {
//            log.info("WorkFlowRestController - file-receive ok, file: {}", file.getOriginalFilename());
//        }

        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute(EMPLOYEE);

        // 응답을 JSON 형식으로 반환
        Map<String, String> response = new HashMap<>();
        if(workFlowService.setAttachmentFileList(files, pk, employeeDTO.getEmployeeName())) {
            response.put("status", "success");
            return ResponseEntity.ok().body(response);
        }
        response.put("status", "fail");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /* Approver Receive 결재자 정보 받기 */
    @PostMapping(value = SEPARATOR + WORKFLOW_URL + APPROVER_SEND, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> approverSend(@Valid @RequestBody List<ApproverDTO> approverDTOS, BindingResult bindingResult) {
        Map<String, String> response = new HashMap<>();
        approverDTOS.forEach(d -> log.info("approver: {}", d));

        if (bindingResult.hasErrors()) {
            response.put("result", "fail");
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.info("WorkFlowRestController - approverSend Field! Error: {}", fieldError);
            }
        }
        else if(!workFlowService.setApproverDTOList(approverDTOS)) { // Service Logic
            log.info("WorkFlowRestController - approverSend, Service Fail");
            response.put("result", "fail");
        } else {
//            log.info("WorkFlowRestController - approverSend, success");
            response.put("result", "success");
            response.put("url", SEPARATOR + WORKFLOW_URL + WORK_STATUS);
        }
        return ResponseEntity.ok().body(response);
    }

    /* Approve Request Receive Approve/Reject/Comment
     * (결재자 요청 받기 승인/반려/코멘트) */
    @PutMapping(value = SEPARATOR + WORKFLOW_URL + APPROVAL + APPROVAL_PARAM)
    public ResponseEntity<Map<String, Object>> approval(
            @PathVariable long workFlowId, @PathVariable long employeeId, @PathVariable int approverType,
            @RequestParam(required = false) Integer approvalMethod, @RequestParam(required = false) String comment) {
//        log.info("WorkFlowRestController - approval ok, param: {}, {}, {}, {}, {}", workFlowId, employeeId, approverType, approvalMethod, comment);
        Map<String, Object> response = new HashMap<>();

        ApproverDTO approverDTO = new ApproverDTO().setWorkFlowId(workFlowId)
                .setApprovalMethod(approvalMethod == null ? 0 : approvalMethod)
                .setEmployeeId(employeeId).setApproverType(approverType).setComment(comment);
        if(workFlowService.setApprover(approverDTO)) {
            notifyService.notifySetup(workFlowId, employeeId, approverType, "workflow 전자결재", (w, e) -> w.getApprovers().stream()
                    .filter(we -> we.getEmployeeId() == e.getEmployeeId())
                    .findFirst().orElse(null) != null ? approverType == 2 ?
                    e.getEmployeeName() + "님이 결재 협의안을 작성하였습니다." :
                    e.getEmployeeName() + "님이 결재를 처리하였습니다." : ""
            );
            String detail = "/detail/";
            response.put("status", "success");
            response.put("url", SEPARATOR + WORKFLOW_URL + detail + workFlowId);
            return ResponseEntity.ok().body(response);
        } else {
            response.put("status", "fail");
            response.put("workFlowId", workFlowId);
            response.put("employeeId", employeeId);
            response.put("approverType", approverType);
            response.put("approvalMethod", approvalMethod);
            response.put("comment", comment);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /* Approval Request Cansel 결재 취소 */
    @DeleteMapping(value = SEPARATOR + WORKFLOW_URL + APPROVAL)
    public ResponseEntity<Map<String, String>> approvalCansel(@RequestParam long workFlowId, @RequestParam int status) {
//        log.info("WorkFlowRestController - approval cansel, param: {}, {}", workFlowId, status);
        Map<String, String> response = new HashMap<>();

        if(status == 0) { /* 결재가 진행 중인 경우만 발송 취소 가능 */
            /* 발송 취소 로직 처리 */
            if(workFlowService.removeWorkflow(workFlowId)) {
                response.put("status", "success");
                response.put("url", SEPARATOR + WORKFLOW_URL + APPROVAL_HISTORY);
                return ResponseEntity.ok().body(response);
            }
        }
        response.put("status", "fail");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /* Approve Attachment File Download 결재 첨부파일 다운로드 */
    @GetMapping(value = SEPARATOR + WORKFLOW_URL + FILE_RECEIVE + SEPARATOR + "{fileId}")
    public ResponseEntity<Resource> fileReceive(@PathVariable long fileId) {
//        log.info("WorkFlowRestController - file-receive ok, id: {}", fileId);

        Map<String, Object> result = workFlowService.getAttachmentFile(fileId);
        if (result != null || (boolean)result.get("result")) {
            HttpHeaders headers = new HttpHeaders();
            Resource resource = (Resource) result.get("fileResource");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + result.get("fileName") + "\"");
            headers.add(HttpHeaders.CONTENT_LENGTH, result.get("fileSize") + "");
            return ResponseEntity.ok().headers(headers).body(resource);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    /* 전자결재 통계 */
    @GetMapping(value = "/work-flow/stat/{code}")
    public ResponseEntity<Object> workStatus(@PathVariable int code, HttpSession session) {
//        log.info("WorkFlowRestController - workStatus ok, param: {}", code);
        return getObjectResponseEntity(code, session, log, workFlowService);
    }

    public static ResponseEntity<Object> getObjectResponseEntity(@PathVariable int code, HttpSession session, Logger log, WorkFlowService workFlowService) {
        SessionEmployeeDTO sessionDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        log.info("WorkFlowRestController - workStatus ok, sessionDTO: {}", sessionDTO);

        Object result = workFlowService.getWorkflowStatistics(sessionDTO.getEmployeeId(), sessionDTO.getDepartment().getDepartmentId(), code);
        if (result != null) {
            log.info("WorkFlowRestController - workStatus ok, result: {}", result);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
