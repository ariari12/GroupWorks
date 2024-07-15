package kr.co.groupworks.control.kah;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.kah.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/vacation")
@RequiredArgsConstructor
public class VacationController {
    private final EmployeeService employeeService;
    private final VacationService vacationService;

    @GetMapping("")
    public String vacationMain(HttpSession session, Model model) {
        log.info("VacationController - vacationMain");

        Long employeeId = (Long) session.getAttribute("employeeId");
        log.info("세션 값 : "+employeeId);

        // 휴가 신청 내역 조회

        // 휴가 보유 사용현황 조회


        // header title 넘겨주기
        model.addAttribute("title", "내 휴가");
        model.addAttribute("subtitle", "휴가 신청내역");
        return "kah/vacationMain";
    }

    @GetMapping(value = "/team")
    public String vacationTeam(Model model) {
        log.info("VacationController - vacationTeam");

        // header title 넘겨주기
        model.addAttribute("title", "구성원 휴가");
        return "kah/vacationTeam";
    }
}
