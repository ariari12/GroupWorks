package kr.co.groupworks.calendar.controller;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @GetMapping
    public String calendar(Model model){
        model.addAttribute("title","캘린더");
        return"calendar/calendar";
    }

}