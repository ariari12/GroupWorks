package kr.co.groupworks.main;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.security.CustomUserDetails;
import kr.co.groupworks.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final EmployeeService employeeService;

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
        sessionEmployeeDTO.setDepartmentId(employee.getDepartmentId());

        session.setAttribute("employee", sessionEmployeeDTO);
        log.info("employee" + sessionEmployeeDTO);
        model.addAttribute("title", "MAIN");
        model.addAttribute("subtitle", "SUBMAIN");
        return "main";
    }

//  로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

// 마이페이지
    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session) {

        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());
        log.info(employeeDTO.getEmployeeName() + "의 마이페이지");
        model.addAttribute("employee", employeeDTO);
        return "employee/myPage";
    }
}
