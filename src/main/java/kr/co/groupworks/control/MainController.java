package kr.co.groupworks.control;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.employee.EmployeeDTO;
import kr.co.groupworks.service.cis.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final EmployeeService employeeService;

    @GetMapping(value = {"/", "/main"})
    public String mainView(Model model, HttpSession session) {

//        로그인이 되어있는 상태
        if(session.getAttribute("employeeId") != null) {
            log.info("MainController - mainView");

            EmployeeDTO employeeDto = new EmployeeDTO()
                    .setEmployeeId(114107)
                    .setEmployeeName("XxAaBb")
                    .setDepartmentId(152115)
                    .setDepartmentName("회계3팀");

            // Test Use Session
            session.setAttribute("employee", employeeDto);

            // header title 넘겨주기
            model.addAttribute("title", "MAIN");
            model.addAttribute("subtitle", "SUBMAIN");
            return "main";
        }
//        로그인이 안되어 있는 상태
        else{
            return "cis/login/loginForm";
        }
    }

    @PostMapping("/loginOk")
    public String loginOk(@RequestParam("name") String name,
                          @RequestParam("pw") String pw,
                          @RequestParam("id") String id, HttpSession session, Model model) {
        System.out.println("로그인 시도 정보 : name : " + name + " pw : " + pw + " id : " + id);
//        로그인 성공 시
        if(employeeService.findByEmployeeIdAndEmployeePWAndEmployeeName(id,pw,name)){
            session.setAttribute("employeeId", id);
            session.setAttribute("employeeName", name);
            session.setAttribute("employeePw", pw);
            System.out.println("로그인 성공");
            return "redirect:/main";
        }else{
            model.addAttribute("msg","로그인을 실패했습니다. 다시 입력해주세요!");
            return "cis/login/loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

}
