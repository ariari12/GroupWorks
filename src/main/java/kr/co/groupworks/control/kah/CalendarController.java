package kr.co.groupworks.control.kah;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {
    @GetMapping("/calendar")
    public String calendar(Model model){
        model.addAttribute("title","캘린더");
        return"kah/calendar";
    }
}