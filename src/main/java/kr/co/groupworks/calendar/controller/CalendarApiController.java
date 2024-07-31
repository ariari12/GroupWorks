package kr.co.groupworks.calendar.controller;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CalendarApiController {
    @PostMapping
    public String createCalendar(@RequestBody CalendarFormDTO calendarFormDTO){
        log.info("CalendarController - createCalendar");
        log.info("{}",calendarFormDTO);
        return"redirect:/calendar";
    }
}
