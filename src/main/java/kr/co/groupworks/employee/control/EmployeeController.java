package kr.co.groupworks.employee.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Hidden
@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    //    사직서 신청 폼 가기
    @GetMapping("/resignation")
    public String resignationForm(HttpSession session, Model model) {
        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());
        model.addAttribute("employee", employeeDTO);
        return "modifyInfo";
    }

    // 마이페이지
    @GetMapping("/mypage")
    public String myPage(Model model, HttpSession session) {

        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());
        log.info(employeeDTO.getEmployeeName() + "의 마이페이지");
        log.info("사수 id" + employeeDTO.getSupervisorId());
        if(employeeDTO.getSupervisorId() != 0L) {
            EmployeeDTO supervisorEmployeeDTO = employeeService.findSupervisorEmployeeByEmployeeId(employeeDTO.getSupervisorId());
            model.addAttribute("supervisorEmployee", supervisorEmployeeDTO);
        }
        model.addAttribute("employee", employeeDTO);
        return "employee/myPage";
    }

    //  내정보변경
    @GetMapping("/modifyInfo")
    public String modifyInfoForm(HttpSession session, Model model) {
        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());
        model.addAttribute("employee", employeeDTO);

        return "employee/modifyInfo";
    }


}
