package kr.co.groupworks.calendar.controller;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;
    @GetMapping
    public String calendar(Model model){
        List<CalendarFormDTO> calendarFormDTOList = calendarService.findAllCalendar();
        model.addAttribute("calendarFormDTO", calendarFormDTOList);
        model.addAttribute("title","캘린더");
        return"calendar/calendar";
    }

}