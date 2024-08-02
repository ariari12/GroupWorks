package kr.co.groupworks.calendar.controller;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.service.CalendarService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        log.info("{}",calendarFormDTOList);
        model.addAttribute("calendarFormDTO", calendarFormDTOList);
        model.addAttribute("title","캘린더");
        return"calendar/calendar";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long calendarId,
                         @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){
        log.info("CalendarController - delete id = {}",calendarId);
        calendarService.deleteCalendar(calendarId, sessionEmployeeDTO.getEmployeeId());
        return "redirect:/calendar";
    }

}