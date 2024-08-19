package kr.co.groupworks.main;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.common.security.CustomUserDetails;
import kr.co.groupworks.employee.service.EmployeeService;
import kr.co.groupworks.workflow.repository.WorkFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Hidden
@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final EmployeeService employeeService;

    private final WorkFlowRepository workFlowRepository

//    로그인 창 이외에는 싹 권한 필요함 첫 화면은 로그인
    @GetMapping("/")
    public String mainView(Model model, HttpSession session) {
        return "login/loginForm";
    }

//    로그인 후 메인화면
    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        log.info("MainController - mainView");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        EmployeeDTO employee = employeeService.findByEmployeeId(Long.valueOf(user.getUsername()));
        System.out.println("로그인 employee : " + employee);

        SessionEmployeeDTO sessionEmployeeDTO = new SessionEmployeeDTO();

        sessionEmployeeDTO.setEmployeeId(employee.getEmployeeId());
        sessionEmployeeDTO.setEmployeeName(employee.getEmployeeName());
        sessionEmployeeDTO.setEmail(employee.getEmail());
        sessionEmployeeDTO.setDepartment(employee.getDepartment());

        session.setAttribute("employee", sessionEmployeeDTO);
        log.info("employee" + sessionEmployeeDTO);
        model.addAttribute("title", "MAIN");
        model.addAttribute("subtitle", "SUBMAIN");
        model.addAttribute("workflowList", workFlowRepository.recentWorkflowList(sessionEmployeeDTO.getEmployeeId()));
        return "main";
    }

//  로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }


}
