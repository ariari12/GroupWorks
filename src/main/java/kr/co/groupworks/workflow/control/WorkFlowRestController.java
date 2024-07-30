package kr.co.groupworks.workflow.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.workflow.dto.dto.ExampleStatusDTO;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import kr.co.groupworks.workflow.dto.employee.EmployeeDTO;
import kr.co.groupworks.workflow.service.WorkFlowService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class WorkFlowRestController {
    private final WorkFlowService workFlowService;

    private static final String WORKFLOW_URL = "work-flow";
    private static final String APPROVAL_HISTORY = "/approval-history";
    private static final String EMPLOYEE = "/employee";
    private static final String FILE_SEND = "/file-send";
    private static final String FILE_RECEIVE = "/file-download";
    private static final String APPROVAL_REQUEST = "/request";
    private static final String APPROVAL = "/approval";
    private static final String APPROVAL_PARAM = "/{workFlowId}/{employeeId}/{approverType}";
    private static final String APPROVER_SEND = "/approver-send";
    private static final String WORK_STATUS = "/stat";
    private static final String STATUS_PARAMETER = "/{param}";
    private static final String SEPARATOR = "/";

    @Getter
    private enum AttributeName {
        TITLE("title"),
        SUB_TITLE("subTitle"),
        EMPLOYEE("employee"),
        ;

        private final String status;

        AttributeName(String status) {
            this.status = status;
        }
    }

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

        SessionEmployeeDTO employeeDTO = (SessionEmployeeDTO) session.getAttribute(AttributeName.EMPLOYEE.getStatus());

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

    /* 전체 사원정보 */
    @GetMapping(value = SEPARATOR + WORKFLOW_URL + EMPLOYEE)
    public ResponseEntity<Map<String, Object>> employee() {
        List<EmployeeDTO> employeeList = workFlowService.getEmployeeAllDTOList();
//        log.info("WorkFlowRestController - employee ok");

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("result", true);
        response.put(AttributeName.EMPLOYEE.getStatus(), employeeList);

        return ResponseEntity.ok().body(response);
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


    @Operation(summary = "결재 통계 데이터",
            description = "로그인한 사원의 사원번와 부서번호를 사용하여 각 종류의 따른 통계데이터 반환, parameter: 반환받을 데이터 종류 Code",
            tags = "work-flow-rest-controller"
    )
    @Parameter(name = "param",
            description = "Api 반환 데이터 종류 Code: { 1 부서 목록, 부서별 결재 발송/승인/반려 건 수, 2: 올해 연도 전체 결재 발송 건 수," +
                    " 3: 부서 결재 완료 목록, 4: 월 별 결재 발송/승인/반려 건, 5: 누적 결재 발송 건 수 }",
            required = true,
            example = "1~6(Code 번호)"
    )
    @ApiResponse(responseCode = "200",
            description = "요청 code에 따른 세션 사원정보에 해당하는 데이터 목록 데이터 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExampleStatusDTO.class))
    )
    @ApiResponse(responseCode = "404", description = "Url 에러", content = @Content(schema = @Schema(example = "404 Not Found Error")))
    @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(schema = @Schema(example = "500 Server Error")))
    @GetMapping(value = SEPARATOR + WORKFLOW_URL + WORK_STATUS + STATUS_PARAMETER)
    public ResponseEntity<Object> workStatus(@PathVariable int param, HttpSession session) {
        log.info("WorkFlowRestController - workStatus ok, param: {}", param);

        SessionEmployeeDTO sessionDTO = (SessionEmployeeDTO) session.getAttribute(AttributeName.EMPLOYEE.getStatus());
        log.info("WorkFlowRestController - workStatus ok, sessionDTO: {}", sessionDTO);

        Object result = workFlowService.getWorkflowStatistics(sessionDTO.getEmployeeId(), sessionDTO.getDepartmentId(), param);
        if (result != null) {
            log.info("WorkFlowRestController - workStatus ok, result: {}", result);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}