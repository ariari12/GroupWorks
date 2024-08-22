package kr.co.groupworks.workflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import kr.co.groupworks.workflow.dto.vo.ApproverVO;
import kr.co.groupworks.workflow.dto.vo.WorkFlowVO;
import kr.co.groupworks.workflow.dto.vo.WorkflowListVO;
import kr.co.groupworks.workflow.service.WorkFlowService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@Hidden
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
    private static final String DIRECTORY = "workflow";

    @Getter
    public enum AttributeName {
        TITLE("title"),
        SUB_TITLE("subtitle"),
        WORK_FLOW_DTO("workFlowDto"),
        EMPLOYEE("employee"),   // SessionEmployee(사원 세션 정보)
        APPROVAl("approval"),   // 승인내역
        PROGRESS("progress"),   // 진행 내역
        REJECTION("rejection"), // 반려 내역
        APPROVERS("approvers"),         // 결재자
        COLLABORATORS("collaborators"), // 협조자
        REFERRESRS("referrers"),        // 참조자
        CLASSIFICATIONS("classifications"),  // 구분
        ATTACHE_FILES("attacheFiles"),  // 첨부파일
        COMMENT_LIST("commentList"),
        REQUEST("request")
        ;

        private final String status;

        AttributeName(String status) {
            this.status = status;
        }
    }

    private String title;
    private String subtitle;

    /* Request Approval(결제 요청) Form */
    @GetMapping( APPROVAL_REQUEST)
    public String approvalRequest(Model model, HttpSession session) {
        // 사원정보 받아오기
        WorkFlowDTO workFlowDTO = workFlowService.getWorkflowDTO(getEmployeeId(session));
        title = "Approval Request";
        subtitle = "전자결재 발송";

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), subtitle);
        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlowDTO);

        return DIRECTORY + "/approvalForm";
    }

    /* Approval History (결재 발송 내역) */
    @GetMapping(APPROVAL_HISTORY)
    public String approvalHistory(Model model, HttpSession session) {
        title = "Approval History";
        subtitle = "전자결재 발송 내역";

        Map<String, List<WorkflowListVO>> result = workFlowService.getMyWorkFlowDTOList(getEmployeeId(session));
        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), subtitle);
        /* approval(승인), progress(진행), rejection(반려) */
        result.forEach(model::addAttribute);

        return DIRECTORY + "/historyList";
    }

    /* Approval History details (결재 상세 내용) */
    @GetMapping(value = DETAIL + "{workflowId}")
    public String detail( HttpSession session, Model model, @PathVariable int workflowId) {
        title = "Approval Detail History";
        subtitle = "전자결재 발송 상세내역";

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), subtitle);
        getDetailAttribute(workFlowService.getDetailWorkFlow(workflowId), getEmployeeId(session))
                .forEach((k, v) -> {
                    if (k.equals("listMap")) {
                        ((Map<String, List<ApproverVO>>) v).forEach(model::addAttribute);
                    } else {
                        model.addAttribute(k, v);
                    }
                }
        );

        return DIRECTORY + "/approvalDetail";
    }

    /* 결재 현황 */
    @GetMapping(WORK_STATUS)
    public String workStat(Model model, HttpSession session) {
        title = "WorkFlow Dashboard";
        subtitle = "전자결재 현황";

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), subtitle);
        model.addAttribute(AttributeName.APPROVAl.getStatus(), workFlowService.getWorkflowStatistics(getEmployeeId(session), getDepartmentId(session), 3));

        return DIRECTORY + "/workStatus";
    }

    /* 결재 대기(승인대기 / 요청대기) 목록 */
    @GetMapping(WORK_WAIT)
    public String workWait(Model model, HttpSession session) {
        title = "WorkFlow Approve Wait List";
        subtitle = "결재 승인대기 목록";
        long employeeId = getEmployeeId(session);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), subtitle);
        workFlowService.getWorkflowWaitList(employeeId).forEach(model::addAttribute);

        return DIRECTORY + "/approvalWaitList";
    }

    /* Referer 참조 / Collaborator 협조 */
    @GetMapping(WORK_REFERR)
    public String workRefer(Model model, HttpSession session) {
        title = "WorkFlow Referer / Collaborator List";
        subtitle = "결재 참조 / 협조 목록";
        long employeeId = getEmployeeId(session);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), subtitle);
        workFlowService.getWorkflowReferrerList(employeeId).forEach(model::addAttribute);

        return DIRECTORY + "/approvalReferrerList";
    }

    /* Workflow Detail View Use **WorkflowDTO -> Attribute Item Convert** */
    private Map<String, Object> getDetailAttribute(WorkFlowDTO workFlow, long employeeId) {
        enum approverClass{ DRAFTER, APPROVER, COLLABORATOR, REFERRER, CURRENT_APPROVER }
        final String[] PARAM = { AttributeName.APPROVERS.getStatus(), AttributeName.COLLABORATORS.getStatus(), AttributeName.REFERRESRS.getStatus(), AttributeName.COMMENT_LIST.getStatus() };
        Map<String, List<ApproverVO>> listMap = Map.of( PARAM[0], new ArrayList<>(), PARAM[1], new ArrayList<>(), PARAM[2], new ArrayList<>(), PARAM[3], new ArrayList<>() );

        /* 사용자가(사원번호pk) 참조자 default:3 */
        int classifications = approverClass.REFERRER.ordinal();
        /* 사용자가(사원번호pk) 기안자:0 */
        if(workFlow.getEmployeeId() == employeeId) classifications = approverClass.DRAFTER.ordinal();

        /* 결재자 / 협조자 / 참조자 정보 */
        for (ApproverDTO a : workFlow.getApprovers()) {
            listMap.get(PARAM[a.getApproverType() -1]).add(new ApproverVO(a));

            /* Comment 작성자 수 확인 */
            if(a.getComment() != null && !a.getComment().trim().isEmpty()) {
                listMap.get(PARAM[3]).add(new ApproverVO(a));
            }
            /* 사용자가(사원번호pk) 현재결재 차례 결재자:4 결재자:1 */
            if (classifications == 3 && a.getApproverType() == 1 && a.getEmployeeId() == employeeId) {
                classifications = (workFlow.getStatus() == 0 || workFlow.getStatus() == 3) && a.getSequenceNum() - 1 == workFlow.getApprovalCount() ?
                        approverClass.CURRENT_APPROVER.ordinal() : approverClass.APPROVER.ordinal();
            }
            /* 사용자가(사원번호pk) 협조자:2
             * 협조자가 코멘트를 작성하면 승인, 작성하지 않은 경우 진행, 진행단계일 때만 코멘트 작성가능 */
            else if(a.getApproverType() == 2 &&(1 <= classifications && classifications <= 3) && a.getApproval() != 1 && a.getEmployeeId() == employeeId) {
                classifications = approverClass.COLLABORATOR.ordinal();
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put(AttributeName.WORK_FLOW_DTO.getStatus(), new WorkFlowVO(workFlow.dtoToEntity()));
        result.put(AttributeName.CLASSIFICATIONS.getStatus(), classifications);
        result.put("listMap", listMap);
        return result;
    }

    /* HttpSession -> EmployeeId */
    private long getEmployeeId(HttpSession session) {
        return ((SessionEmployeeDTO) session.getAttribute(AttributeName.EMPLOYEE.getStatus())).getEmployeeId();
    }

    private long getDepartmentId(HttpSession session) {
        return ((SessionEmployeeDTO) session.getAttribute(AttributeName.EMPLOYEE.getStatus())).getDepartment().getDepartmentId();
    }
}