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
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/work-flow")
@RequiredArgsConstructor
public class WorkFlowController {

    private static final String WORKFLOW_URL = "work-flow";
    private static final String APPROVAL_REQUEST = "/request";
    private static final String MY_APPROVED = "/my-approved";
    private static final String WORK_STATUS = "/stat";
    private static final String WORK_WAIT = "/wait";
    private static final String REQUEST_OK = "/request-ok";
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
    @GetMapping(APPROVAL_REQUEST)
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
        log.info("WorkFlowController - request title: {}", title);

        model.addAttribute(AttributeName.TITLE.getStatus(), title);
        model.addAttribute(AttributeName.SUB_TITLE.getStatus(), title);
        model.addAttribute(AttributeName.WORK_FLOW_URL.getStatus(), WORK_STATUS);
        model.addAttribute(AttributeName.WORK_FLOW_DTO.getStatus(), workFlowDTO);

        return "workflow/approvalForm";
    }

    @GetMapping(MY_APPROVED)
    public String myApproved(Model model, HttpSession session) {
        log.info("WorkFlowController - myApproved");

        return "workflow/myRequest";
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

    /* 결재 내용 상세보기 및 결재 승인하기 */
    /* 결재 요청 받기 */
    @PostMapping(REQUEST_OK)
    public String requestOk(@ModelAttribute WorkFlowDTO workFlowDTO) {
        log.info("WorkFlowController - request ok, workFlowDTO: {}", workFlowDTO);

        return "redirect:" + SEPARATOR + WORKFLOW_URL + WORK_STATUS;
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

    @PostMapping(FILE_SEND)
    @ResponseBody
    public Map<String, String> fileSendTest(
            @RequestParam("attach_file") MultipartFile[] files) {
        log.info("WorkFlowController - request ok, files: {}", files.length);
        for (MultipartFile file : files) {
            log.info("WorkFlowController - request ok, file: {}", file.getOriginalFilename());
        }

        // 응답을 JSON 형식으로 반환
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return response;
    }
}
