package kr.co.groupworks.control.kah;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/vacation")
public class VacationController {

    @GetMapping("")
    public String vacationMain(Model model) {
        log.info("VacationController - vacationMain");

        // header title 넘겨주기
        model.addAttribute("title", "휴가");
        return "kah/vacationMain";
    }

    @GetMapping(value = "/team")
    public String vacationTeam(Model model) {
        log.info("VacationController - vacationTeam");

        // header title 넘겨주기
        model.addAttribute("title", "구성원 휴가");
        model.addAttribute("subtitle", "구성원 휴가");
        return "kah/vacationTeam";
    }
}
