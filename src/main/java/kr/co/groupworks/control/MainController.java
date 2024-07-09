package kr.co.groupworks.control;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String mainView(Model model, HttpSession session) {
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
        return "main";
    }
}
