package kr.co.groupworks.attendance.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/commute")
public class CommuteController {

    @GetMapping("/list")
    public String commute(Model model) {
        model.addAttribute("title", "근태 현황");
        return "attendance/correction";
    }

    @GetMapping("/manage")
    public String commuteCorrection(Model model) {
        return "attendance/manage";
    }
}
