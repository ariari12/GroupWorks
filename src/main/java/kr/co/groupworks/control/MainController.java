package kr.co.groupworks.control;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.security.CustomUserDetails;
import kr.co.groupworks.service.cis.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    @GetMapping(value = {"/", "/main"})
//    public String mainView(Model model, HttpSession session) {
//
////        로그인이 되어있는 상태
//        if(session.getAttribute("employeeId") != null) {
//            log.info("MainController - mainView");
//
//            model.addAttribute("title", "MAIN");
//            model.addAttribute("subtitle", "SUBMAIN");
//            return "main";
//        }
////        로그인이 안되어 있는 상태
//        else{
//            return "cis/login/loginForm";
//        }
//    }


//    로그인 창 이외에는 싹 권한 필요함 첫 화면은 로그인
    @GetMapping("/")
    public String mainView(Model model, HttpSession session) {
        return "cis/login/loginForm";
    }
//      로그인 과정
//    @PostMapping("/loginProc")
//    public String loginOk(@RequestParam("username") String name,
//                          @RequestParam("password") String pw,
//                          @RequestParam("id") String id, HttpSession session, Model model) {
//        System.out.println("로그인 시도 정보 : name : " + name + " pw : " + pw + " id : " + id);
//
//        //        로그인 성공 시
//        if(employeeService.findByEmployeeIdAndEmployeePWAndEmployeeName(id,pw,name)){
//            session.setAttribute("employeeId", id);
//            session.setAttribute("employeeName", name);
//            System.out.println("로그인 성공");
//            return "redirect:/main";
//        }else{
//            model.addAttribute("msg","로그인을 실패했습니다. 다시 입력해주세요!");
//            return "cis/login/loginForm";
//        }
//    }
//    로그인 후 메인화면
    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        log.info("MainController - mainView");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        Employee employee = employeeService.findByEmployeeId(Long.valueOf(user.getUsername()));
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
        log.info("MainController - myPage");
        return "cis/employee/myPage";
    }
}
