package kr.co.groupworks.control.ljm;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import kr.co.groupworks.dto.ljm.workflow.ApproverDTO;
import kr.co.groupworks.dto.ljm.workflow.AttachmentFileDTO;
import kr.co.groupworks.dto.ljm.workflow.WorkFlowInsertDTO;
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

        WorkFlowInsertDTO workFlowDTO = new WorkFlowInsertDTO()
            .setEmployeeId(employeeDTO.getEmployeeId())
            .setEmployeeName(employeeDTO.getEmployeeName())
            .setDepartment(employeeDTO.getDepartmentName());

        title = "Approval Request";
        log.info("");
        log.info("WorkFlowController - request title: {}, setDto: {}", title, workFlowDTO);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlowDTO);

        return "workflow/approvalForm";
    }

    /* 결재 발송 내역 */
    @GetMapping(APPROVAL_HISTORY)
    public String approvalHistory(Model model) {
        title = "Approval History";
        log.info("WorkFlowController - approval history");

        /* 승인 내역 */
        List<WorkFlowInsertDTO> approval = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            approval.add(
                new WorkFlowInsertDTO()
                        .setId(i)
                        .setEmployeeId(i)
                        .setEmail("text@text.com")
                        .setEmployeeRank("사원")
                        .setDepartment("부서명")
                        .setEmployeeName("기안자명")
                        .setWorkFlowType("업무결재")
                        .setCode("12121212-1-1212121122")
                        .setTitle("Approval History Test" + i)
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
        List<WorkFlowInsertDTO> progress = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            progress.add(
                new WorkFlowInsertDTO()
                        .setId(i *4)
                        .setEmployeeId(i)
                        .setEmail("text@text.com")
                        .setEmployeeRank("사원")
                        .setDepartment("부서명")
                        .setEmployeeName("기안자명")
                        .setWorkFlowType("업무결재")
                        .setCode("12121212-1-1212121122")
                        .setTitle("Approval History Test" + (i *4))
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
        List<WorkFlowInsertDTO> rejection = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            rejection.add(
                new WorkFlowInsertDTO()
                        .setId(i *8)
                        .setEmployeeId(i)
                        .setEmail("text@text.com")
                        .setDepartment("부서명")
                        .setEmployeeRank("사원")
                        .setEmployeeName("기안자명")
                        .setWorkFlowType("업무결재")
                        .setCode("12121212-1-1212121122")
                        .setTitle("Approval History Test" + (i *8))
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

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        model.addAttribute(AttributeName.APPROVAl.getStatus(), approval);   // 승인내역
        model.addAttribute(AttributeName.PROGRESS.getStatus(), progress);   // 진행내역
        model.addAttribute(AttributeName.REJECTION.getStatus(), rejection); // 반려내역
        return "workflow/historyList";
    }

    /* 결재 상세 내용 */
    @GetMapping(value = DETAIL + "{id}")
    public String detail( HttpSession session, Model model, @PathVariable int id) {
        title = "Approval Detail";
        log.info("WorkFlowController - approval detail, id: {}, title: {}", id, title);

        // 세션에서 사원 정보 가져오기
        EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");

        WorkFlowInsertDTO workFlow = new WorkFlowInsertDTO()
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
                            .setId(i)
                            .setWorkFlowId(1)
                            .setSequenceNum(i)
                            .setApproverType(1) // 1:결재자
                            .setEmployeeId(i)
                            .setApproverName("결재자" +i)
                            .setApproverRank("사원" +i)
                            .setDepartment("부서" +i)
//                            .setApprovalMethodInt(1)
                            .setApprovalMethodStr("선결")
                            .setApprovalDate(LocalDateTime.now().format(
                                    DateTimeFormatter.ofPattern("yyyy/MM/dd")
                            ))
//                            .setApproval(true)
                            .setApprovalStr("승인")
            );
        }
        approvers.set(2, approvers.get(2).setApprovalStr("반려"));
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
                            .setId(i)
                            .setWorkFlowId(1)
                            .setSequenceNum(i -3)
                            .setApproverType(2) // 2:협조자
                            .setEmployeeId(i)
                        .setApproverName("협조자" +(i-3))
                            .setApproverRank("사원" +i)
                            .setDepartment("부서" +i)
//                            .setApprovalMethodInt(0) // 협조자는 결재X
                            .setApprovalMethodStr("")// 협조자는 결재X
//                            .setApproval(true)
                            .setApprovalStr("")
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
                        .setId(4)
                        .setWorkFlowId(1)
                        .setSequenceNum(1)
                        .setApproverType(2)
                        .setEmployeeId(4)
                        .setApproverName("참조자")
                        .setApproverRank("사원5")
                        .setDepartment("부서5")
//                        .setApprovalMethodInt(0)
                        .setApprovalMethodStr("")
//                        .setApproval(true)
                        .setApprovalStr("")
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

        return "workflow/approvalDetail";
    }

    /* 결재 현황 */
    @GetMapping(WORK_STATUS)
    public String workStat(Model model) {
        title = "WorkFlow Status Board";
        log.info("WorkFlowController - stat title: {}", title);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);

        return "workflow/workStatus";
    }

    /* 결재 대기(승인대기 / 요청대기) 목록 */
    @GetMapping(WORK_WAIT)
    public String workWait(Model model) {
        title = "WorkFlow Wait";
        log.info("WorkFlowController - wait");

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        return "workflow/approvalWaitList";
    }

}
