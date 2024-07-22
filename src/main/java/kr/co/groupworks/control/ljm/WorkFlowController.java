package kr.co.groupworks.control.ljm;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.AttachmentFileDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowDTO;
import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.service.ljm.WorkFlowService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/work-flow")
@RequiredArgsConstructor
public class WorkFlowController {
    private final WorkFlowService workFlowService;

    private static final String TITLE = "Work Flow";
    private static final String WORKFLOW_URL = "work-flow";
    private static final String DETAIL = "/detail/";
    private static final String APPROVAL_HISTORY = "/approval-history";
    private static final String APPROVAL_REQUEST = "/request";
    private static final String WORK_STATUS = "/stat";
    private static final String WORK_WAIT = "/wait";
    private static final String WORK_REFERR = "/referr";
    private static final String SEPARATOR = "/";
    private static final String DIRECTORY = "/ljm-workflow";

    @Getter
    public enum AttributeName {
        TITLE("title"),
        SUB_TITLE("subTitle"),
//        WORK_FLOW_URL("workFlowUrl"),
        WORK_FLOW_DTO("workFlowDto"),
        EMPLOYEE("employee"), // 사원 전체 정보
        EMPLOYEE_ID("employeeId"), // 사원정보 세션 가져오기 용도
        APPROVAl("approval"),   // 승인내역
        PROGRESS("progress"),   // 진행 내역
        REJECTION("rejection")  // 반려 내역
        ;

        private final String status;

        AttributeName(String status) {
            this.status = status;
        }
    }

    private String title;

    /* Request Approval(결제 요청) Form */
    @GetMapping( APPROVAL_REQUEST)
    public String approvalRequest(Model model, HttpSession session) {
        // 사원정보 받아오기
        long employeeId = (long) session.getAttribute(AttributeName.EMPLOYEE_ID.getStatus());
        WorkFlowDTO workFlowDTO = workFlowService.getWorkflowDTO(employeeId);

        title = "Approval Request";
        log.info("WorkFlowController - request title: {}, setDto: {}", title, workFlowDTO);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlowDTO);

        return DIRECTORY + "/approvalForm";
    }

    /* Approval History (결재 발송 내역) */
    @GetMapping(APPROVAL_HISTORY)
    public String approvalHistory(Model model, HttpSession session) {
        title = "Approval History";
        log.info("WorkFlowController - approval history");

        Map<String, List<WorkFlowDTO>> result = workFlowService.getMyWorkFlowDTOList((long) session.getAttribute(AttributeName.EMPLOYEE_ID.getStatus()));

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        // approval(승인), progress(진행), rejection(반려)
        for (String key : result.keySet()) {
            model.addAttribute(key, result.get(key));
        }
        return DIRECTORY + "/historyList";
    }

    /* Approval History details (결재 상세 내용) */
    @GetMapping(value = DETAIL + "{id}")
    public String detail( HttpSession session, Model model, @PathVariable int id) {
        title = "Approval Detail";
        log.info("WorkFlowController - approval detail, id: {}, title: {}", id, title);
        
        int result = 0; // default: refer(참조자)

        // getSession -> Employee Data Set (세션으로 사원정보 가져오기)
        EmployeeDTO employeeDTO = workFlowService.getEmployeeDTO((long) session.getAttribute(AttributeName.EMPLOYEE_ID.getStatus()));

        WorkFlowDTO workFlow = new WorkFlowDTO();
        log.info("WorkFlowController - approval detail, WorkFlowDTO: {}", workFlow);
        /* TODO: 사용자가(사원번호pk) 기안자인지 확인 
         * 사용자가 기안자라면 현재 승인한 결재자가 있는지 확인
         * 확인방법: 결재를 한 사람의 수가 1보다 작으면 결재자존재 X
         * 결재한 사람이 없다면 발송취소가 가능하도록 변수 넘겨주기
         * 결재한 사람이 있다면 발송취소가 불가능하도록 변수 넘겨주기
         * Model Attribute
         */

        /* 결재자 정보 */
        List<ApproverDTO> approvers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            approvers.add(
                    new ApproverDTO()
            );
        }
        /* TODO: 사용자 사원pk가 현재 결재자에 해당 하는지 확인
        *  확인방법: 현재 결재한 사람의 수(반려가 아닌경우) +1 == 
        *    사용자pk가 해당하는 index의 번호
        *  두 숫자가 같으면 사용자 사원은 해당 결재정보에 결재할 차례
        *  Model Attirbute에 해당 사용자가 결재자인걸 확인할 수 있는 변수 넘겨주기
        */
        
        /* 협조자 정보 */
        List<ApproverDTO> collaborators = new ArrayList<>();
        for (int i = 4; i < 6; i++) {
            collaborators.add(
                    new ApproverDTO()
            );
        }
        // 코멘트
        collaborators.set(0, collaborators.get(0)
                .setComment("괜찮은 것 같습니다." +0)
                .setApprovalDate(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                )
        );
        /* TODO: 사용자pk가 협조자에 해당하는 지 확인
         * 사용자가 협조자이고 코멘트를 작성했는지 확인
         * 코멘트를 작성하지 않았다면 작성가능하도록
         * Model Attibute로 Comment 작성란이 활성화 될 수 있도록 변수 넘겨주기
         */

        /* 참조자 정보 */
        List<ApproverDTO> referrers = new ArrayList<>();
        referrers.add(
                new ApproverDTO()
        );
        /* TODO: 사용자가(사원번호pk) 참조자인지 확인
         * 기안자 부서번호 혹은 부서명이 사용자와 일치하다면
         * 사용자는 기안자와 같은 부서이므로 참조자이다.
         * 사용자가 참조자(기안자, 결재자, 협조자 중 어느것에도 속하지 않음)
         * 라면, 참조자인것으르 확인할 수 있도록 Model Attribute 변수 넘기기
         */

        List<AttachmentFileDTO> attachmentDTOS = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            attachmentDTOS.add(
                    new AttachmentFileDTO()
                            .setId(i)
                            .setWorkFlowId(1)
                            .setFileName("Test.Test")
                            .setSavePath("Test.Test")
            );
        }

        model.addAttribute(AttributeName.TITLE.getStatus(), workFlow.getTitle());
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), "");
        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlow);
        model.addAttribute("approvers", approvers);
        model.addAttribute("collaborators", collaborators);
        model.addAttribute("referrers", referrers);

        // Test Variable
        final boolean TEST_VARIABLE = true;
        model.addAttribute("changePossible", TEST_VARIABLE ? true : false);

        return DIRECTORY + "/approvalDetail";
    }

    /* 결재 현황 */
    @GetMapping(WORK_STATUS)
    public String workStat(Model model) {
        title = "WorkFlow Status Board";
        log.info("WorkFlowController - stat title: {}", title);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);

        return DIRECTORY + "/workStatus";
    }

    /* 결재 대기(승인대기 / 요청대기) 목록 */
    @GetMapping(WORK_WAIT)
    public String workWait(Model model) {
        title = "WorkFlow Wait";
        log.info("WorkFlowController - wait");

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        return DIRECTORY + "/approvalWaitList";
    }

    /* 결재 대기(승인대기 / 요청대기) 목록 */
    @GetMapping(WORK_REFERR)
    public String workReferr(Model model) {
        title = "WorkFlow Refer";
        log.info("WorkFlowController - wait");

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        return DIRECTORY + "/approvalWaitList";
    }


}