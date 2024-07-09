package kr.co.groupworks.control.workflow;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.employee.EmployeeDTO;
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
    private static final String APPROVAL_HISTORY = "/approval-history";
    private static final String APPROVAL_REQUEST = "/request";
    private static final String WORK_STATUS = "/stat";
    private static final String WORK_WAIT = "/wait";
    private static final String SEPARATOR = "/";

    @Getter
    private enum AttributeName {
        TITLE("title"),
        SUB_TITLE("subTitle"),
        WORK_FLOW_URL("workFlowUrl"),
        WORK_FLOW_DTO("workFlowDto"),
        EMPLOYEE("employee"),
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
            .setDepartmentName("회계3팀");
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
        /* 승인 내역 */
        List<WorkFlowDTO> approvalList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            approvalList.add(
                new WorkFlowDTO()
                    .setEmployeeId(i)
                    .setEmployeeName("기안자명")
                    .setDraftDate(LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .setApprovalDate(LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .setTitle("Approval History")
                    .setCode("12121212-1-1212121122")
                    .setWorkFlowType("전결")
                    .setApproverCount(3)
                    .setApprovalCount(3)
                    .setDepartment("부서명")
                    .setCost(1000000)
                    .setEmployeeRank("사원")
                    .setStatus("승인")
            );
        }

        /* 진행 내역 */
        List<WorkFlowDTO> progress = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            progress.add(
                new WorkFlowDTO()
                    .setEmployeeId(i)
                    .setEmployeeName("기안자명")
                    .setDraftDate(LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .setTitle("Approval History")
                    .setCode("12121212-1-1212121122")
                    .setApproverCount(3)
                    .setApprovalCount(1)
                    .setDepartment("부서명")
                    .setCost(1000000)
                    .setEmployeeRank("사원")
                    .setStatus("진행")
            );
        }


        /* 반려 내역 */
        List<WorkFlowDTO> rejection = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            rejection.add(
                new WorkFlowDTO()
                    .setEmployeeId(i)
                    .setEmployeeName("기안자명")
                    .setDraftDate(LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .setDraftDate(LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                    .setTitle("Approval History")
                    .setCode("12121212-1-1212121122")
                    .setApproverCount(3)
                    .setApprovalCount(1)
                    .setDepartment("부서명")
                    .setCost(1000000)
                    .setEmployeeRank("사원")
                    .setStatus("반려")
            );
        }

        model.addAttribute("approvalList", approvalList);
        model.addAttribute("progress", progress);
        model.addAttribute("rejection", rejection);
        return "workflow/approvalHistory";
    }


    /* 결재 현황 */
    @GetMapping(WORK_STATUS)
    public String workStat(Model model) {
        title = "WorkFlow Status Board";
        log.info("WorkFlowController - stat title: {}", title);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        model.addAttribute(AttributeName.WORK_FLOW_URL.getStatus(), WORK_STATUS);
        return "workflow/workStatus";
    }

    /* 결재 대기(승인대기 / 요청대기) 목록 */
    @GetMapping(WORK_WAIT)
    public String workWait(Model model) {
        title = "WorkFlow Wait";
        log.info("WorkFlowController - wait");

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        model.addAttribute(AttributeName.WORK_FLOW_URL.getStatus(), WORK_STATUS);
        return "workflow/workWait";
    }

    /* Test Controller */
    private static final String TEST = "/test";
    private static final String FILE_SEND = "/file-send";

    @PostMapping(TEST)
    @ResponseBody
    public String[] test(
            @RequestParam("names") String[] names,
            Model model) {
        log.info("WorkFlowController - test");

        for (String s: names) {
            log.info("WorkFlowController - test, name = {}", s);
        }

        return names;
    }
}
