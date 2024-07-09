package kr.co.groupworks.control.workflow;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.employee.EmployeeDTO;
import kr.co.groupworks.dto.workflow.ApprovalAttachmentDTO;
import kr.co.groupworks.dto.workflow.ApproverDTO;
import kr.co.groupworks.dto.workflow.WorkFlowDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/work-flow")
@RequiredArgsConstructor
public class WorkFlowController {

    private static final String TITLE = "Work Flow";
    private static final String WORKFLOW_URL = "work-flow";
    private static final String DETAIL = "/detail/";
    private static final String APPROVAL_HISTORY = "/approval-history";
    private static final String APPROVAL_REQUEST = "/request";
    private static final String WORK_STATUS = "/stat";
    private static final String WORK_WAIT = "/wait";
    private static final String SEPARATOR = "/";

    @Getter
    private enum AttributeName {
        TITLE("title"),
        SUB_TITLE("subTitle"),
//        WORK_FLOW_URL("workFlowUrl"),
        WORK_FLOW_DTO("workFlowDto"),
        EMPLOYEE("employee"),   // Test 용 사원정보 세션 생성 용도
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

    /* 결제 요청 Form */
    @GetMapping( APPROVAL_REQUEST)
    public String approvalRequest(Model model, HttpSession session) {
        // Test Use Session
        EmployeeDTO employeeDto = new EmployeeDTO()
            .setEmployeeId(114107)
            .setEmployeeName("XxAaBb")
            .setDepartmentId(152115)
            .setDepartmentName("회계3팀")
            .setRankName("사원")
            .setEmail("text@text.com")
            ;
        session.setAttribute("employee", employeeDto);

        // 사원정보 받아오기
        EmployeeDTO employeeDTO = (EmployeeDTO) session
                .getAttribute(AttributeName.EMPLOYEE.getStatus());

        WorkFlowDTO workFlowDTO = new WorkFlowDTO()
            .setEmployeeId(employeeDTO.getEmployeeId())
            .setEmployeeName(employeeDTO.getEmployeeName())
            .setDepartment(employeeDTO.getDepartmentName());

        title = "Approval Request";
        log.info("");
        log.info("WorkFlowController - request title: {}, setDto: {}", title, workFlowDTO);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
//        model.addAttribute(AttributeName.WORK_FLOW_URL.getStatus(), WORK_STATUS);
        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlowDTO);

        return "workflow/approvalForm";
    }

    /* 결재 발송 내역 */
    @GetMapping(APPROVAL_HISTORY)
    public String approvalHistory(Model model) {
        log.info("WorkFlowController - approval history");

        /* 승인 내역 */
        List<WorkFlowDTO> approval = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            approval.add(
                new WorkFlowDTO()
                        .setId(i)
                        .setEmployeeId(i)
                        .setEmail("text@text.com")
                        .setEmployeeRank("사원")
                        .setDepartment("부서명")
                        .setEmployeeName("기안자명")
                        .setWorkFlowType("업무결재")
                        .setCode("12121212-1-1212121122")
                        .setTitle("Approval History")
                        .setDescription("결재요청 테스트")
                        .setDraftDate(LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .setApprovalDate(LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .setFinalApprovalRank("과장")
                        .setFinalApprovalDepartment("부서명")
                        .setFinalApprovalName("결재자명")
                        .setApproverCount(3)
                        .setApprovalCount(3)
                        .setCost(1000000)
                        .setStatus("승인")
            );
        }

        /* 진행 내역 */
        List<WorkFlowDTO> progress = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            progress.add(
                new WorkFlowDTO()
                        .setId(i *4)
                        .setEmployeeId(i)
                        .setEmail("text@text.com")
                        .setEmployeeRank("사원")
                        .setDepartment("부서명")
                        .setEmployeeName("기안자명")
                        .setWorkFlowType("업무결재")
                        .setCode("12121212-1-1212121122")
                        .setTitle("Approval History")
                        .setDescription("결재요청 테스트")
                        .setDraftDate(LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .setFinalApprovalRank("과장")
                        .setFinalApprovalDepartment("부서명")
                        .setFinalApprovalName("결재자명")
                        .setApproverCount((i % 5) +1)
                        .setApprovalCount(i % 5)
                        .setCost(1000000)
                        .setStatus("진행")
            );
        }

        /* 반려 내역 */
        List<WorkFlowDTO> rejection = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            rejection.add(
                new WorkFlowDTO()
                        .setId(i *8)
                        .setEmployeeId(i)
                        .setEmail("text@text.com")
                        .setDepartment("부서명")
                        .setEmployeeRank("사원")
                        .setEmployeeName("기안자명")
                        .setWorkFlowType("업무결재")
                        .setCode("12121212-1-1212121122")
                        .setTitle("Approval History")
                        .setDescription("결재요청 테스트")
                        .setDraftDate(LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .setApprovalDate(LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                        .setFinalApprovalRank("과장")
                        .setFinalApprovalDepartment("부서명")
                        .setFinalApprovalName("결재자명")
                        .setApproverCount(3)
                        .setApprovalCount(2)
                        .setCost(1000000)
                        .setStatus("반려")
            );
        }

        model.addAttribute(AttributeName.APPROVAl.getStatus(), approval);   // 승인내역
        model.addAttribute(AttributeName.PROGRESS.getStatus(), progress);   // 진행내역
        model.addAttribute(AttributeName.REJECTION.getStatus(), rejection); // 반려내역
        return "workflow/historyList";
    }

    /* 결재 상세 내용 */
    @GetMapping(value = DETAIL + "{id}")
    public String detail(
            HttpSession session,
            Model model,
            @PathVariable int id) {
        log.info("WorkFlowController - approval detail, id: {}", id);

        // 세션에서 사원 정보 가져오기
        EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");

        WorkFlowDTO workFlow = new WorkFlowDTO()
                .setId(1)
                .setEmployeeId(employee.getEmployeeId())
                .setEmail("text@text.com")
                .setEmployeeRank(employee.getRankName())
                .setDepartment(employee.getDepartmentName())
                .setEmployeeName(employee.getEmployeeName())
                .setWorkFlowType("업무결재")
                .setCode("12121212-1-1212121122")
                .setTitle("Approval History")
                .setDescription("결재요청 테스트")
                .setDraftDate(LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .setApprovalDate(LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .setFinalApprovalRank("과장")
                .setFinalApprovalDepartment("부서명")
                .setFinalApprovalName("결재자명")
                .setApproverCount(3)
                .setApprovalCount(3)
                .setCost(1000000)
                .setStatus("승인");

        /* 결재자 정보 */
        List<ApproverDTO> approvers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            approvers.add(
                    new ApproverDTO()
                            .setId(i)
                            .setWorkFlowId(1)
                            .setSequenceNum(i)
                            .setApproverType(1)
                            .setEmployeeId(i)
                            .setApproverName("결재자" +i)
                            .setApproverRank("사원" +i)
                            .setDepartment("부서" +i)
                            .setApprovalMethodInt(1)
                            .setApprovalMethodStr("선결")
                            .setApprovalDate(LocalDateTime.now().format(
                                    DateTimeFormatter.ofPattern("yyyy/MM/dd")
                            ))
                            .setApproval(true)
                            .setApprovalStr("승인")
            );
        }
        
        /* 협조자 정보 */
        List<ApproverDTO> collaborators = new ArrayList<>();
        collaborators.add(
                new ApproverDTO()
                        .setId(4)
                        .setWorkFlowId(1)
                        .setSequenceNum(1)
                        .setApproverType(2)
                        .setEmployeeId(4)
                        .setApproverName("협조자")
                        .setApproverRank("사원4")
                        .setDepartment("부서4")
                        .setApprovalMethodInt(0)
                        .setApprovalMethodStr("")
                        .setComment("괜찮은 것 같습니다.")
                        .setApproval(true)
                        .setApprovalStr("")
        );

        /* 참조자 정보 */
        List<ApproverDTO> referrers = new ArrayList<>();
        referrers.add(
                new ApproverDTO()
                        .setId(4)
                        .setWorkFlowId(1)
                        .setSequenceNum(1)
                        .setApproverType(2)
                        .setEmployeeId(4)
                        .setApproverName("참조자")
                        .setApproverRank("사원5")
                        .setDepartment("부서5")
                        .setApprovalMethodInt(0)
                        .setApprovalMethodStr("")
                        .setApproval(true)
                        .setApprovalStr("")
        );


        List<ApprovalAttachmentDTO> attachmentDTOS = new ArrayList<>();
        referrers.add(
                new ApproverDTO()
                        .setId(4)
                        .setWorkFlowId(1)
                        .setSequenceNum(1)
                        .setApproverType(2)
                        .setEmployeeId(4)
                        .setApproverName("참조자")
                        .setApproverRank("사원5")
                        .setDepartment("부서5")
                        .setApprovalMethodInt(0)
                        .setApprovalMethodStr("")
                        .setApproval(true)
                        .setApprovalStr("")
        );

        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlow);
        model.addAttribute("approvers", approvers);
        model.addAttribute("collaborators", collaborators);
        model.addAttribute("referrers", referrers);

        return "workflow/approvalDetail";
    }

    /* 결재 현황 */
    @GetMapping(WORK_STATUS)
    public String workStat(Model model) {
        title = "WorkFlow Status Board";
        log.info("WorkFlowController - stat title: {}", title);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
//        model.addAttribute(AttributeName.WORK_FLOW_URL.getStatus(), WORK_STATUS);
        return "workflow/workStatus";
    }

    /* 결재 대기(승인대기 / 요청대기) 목록 */
    @GetMapping(WORK_WAIT)
    public String workWait(Model model) {
        title = "WorkFlow Wait";
        log.info("WorkFlowController - wait");

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
//        model.addAttribute(AttributeName.WORK_FLOW_URL.getStatus(), WORK_STATUS);
        return "workflow/workWait";
    }

}
