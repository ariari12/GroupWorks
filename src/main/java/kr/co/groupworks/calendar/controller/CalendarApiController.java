package kr.co.groupworks.calendar.controller;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.service.CalendarService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarApiController {
    private final CalendarService calendarService;
    @PostMapping("/save")
    public ResponseEntity<String> createCalendar(@RequestBody CalendarFormDTO calendarFormDTO,
                                                 @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){
        log.info("CalendarController - createCalendar");
        System.out.println("Received event: " + calendarFormDTO);

        calendarService.saveCalendar(calendarFormDTO, sessionEmployeeDTO.getEmployeeId());

        // 성공적으로 저장되었음을 응답합니다.
        return ResponseEntity.status(HttpStatus.OK).body("일정이 성공적으로 추가되었습니다.");
    }
}
