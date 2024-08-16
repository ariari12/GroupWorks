package kr.co.groupworks.calendar.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(
            tags = "개인 일정 (Calendar)",
            summary = "개인 일정 생성",
            description = "새로운 개인 일정을 생성합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "일정이 성공적으로 추가되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping("/save")
    public ResponseEntity<String> createCalendar(
            @Parameter(
                    name = "calendarFormDTO",
                    description = "생성할 개인 일정의 세부 정보가 포함된 DTO",
                    required = true
            )
            @RequestBody CalendarFormDTO calendarFormDTO,

            @Parameter(
                    name = "sessionEmployeeDTO",
                    description = "세션에 저장된 현재 사용자 정보",
                    required = true
            )
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO
    ) {
        calendarService.saveCalendar(calendarFormDTO, sessionEmployeeDTO.getEmployeeId());

        // 성공적으로 저장되었음을 응답합니다.
        return ResponseEntity.status(HttpStatus.OK).body("일정이 성공적으로 추가되었습니다.");
    }
}
