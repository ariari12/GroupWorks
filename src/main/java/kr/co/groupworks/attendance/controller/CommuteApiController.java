package kr.co.groupworks.attendance.controller;

import kr.co.groupworks.attendance.dto.AttendanceDTO;
import kr.co.groupworks.attendance.service.AttendanceService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commute")
public class CommuteApiController {

    private final AttendanceService attendanceService;

    // 리스트
    @GetMapping("/list")
    public List<AttendanceDTO> getAttendance(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO,
                                             @RequestParam(value = "month", required = false) String monthParam) {
        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        // month 파라미터가 없으면 현재 월을 사용
        YearMonth month;
        if (monthParam == null || monthParam.isEmpty()) {
            month = YearMonth.now();
        } else {
            try {
                month = YearMonth.parse(monthParam, DateTimeFormatter.ofPattern("yyyy-MM"));
            } catch (DateTimeParseException e) {
                // 파라미터 형식이 잘못된 경우 예외 처리 또는 기본 월 사용
                month = YearMonth.now();
            }
        }
        return attendanceService.getAttendanceByEmployeeIdAndMonth(employeeId, month);
    }

    // 출퇴근 상태
    @GetMapping("/status")
    public ResponseEntity<String> getCommuteStatus(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {
        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        LocalDate today = LocalDate.now();

        boolean isClockedIn = attendanceService.isClockedIn(employeeId, today);
        boolean isClockedOut = attendanceService.isClockedOut(employeeId, today);

        System.out.println(isClockedOut);

        if (isClockedOut) {
            return ResponseEntity.ok("CLOCKED_OUT");
        } else if (isClockedIn) {
            return ResponseEntity.ok("CLOCKED_IN");
        } else {
            return ResponseEntity.ok("NOT_CLOCKED_IN");
        }
    }

    // 출근 기록 저장
    @PostMapping("/clock-in")
    public ResponseEntity<?> clockIn(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        try {
            Long employeeId = sessionEmployeeDTO.getEmployeeId();
            attendanceService.clockIn(employeeId);
            return ResponseEntity.ok("출근이 성공적으로 기록되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("오늘 이미 출근 기록이 있습니다.");
        }
    }

    // 퇴근 기록 저장
    @PostMapping("/clock-out")
    public ResponseEntity<?> clockOut(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        try {
            Long employeeId = sessionEmployeeDTO.getEmployeeId();
            attendanceService.clockOut(employeeId);
            return ResponseEntity.ok("퇴근이 성공적으로 기록되었습니다.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("오늘 이미 퇴근 기록이 있습니다.");
        }
    }

    // 근태 추가
    @PostMapping("/add-requests")
    public ResponseEntity<?> addRequests(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        return ResponseEntity.badRequest().body("null");
    }

    // 근태 수정
    @PutMapping("/edit-requests")
    public ResponseEntity<?> editRequests(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        return ResponseEntity.badRequest().body("null");
    }

    // 근태 요청 리스트
    @GetMapping("/view-requests")
    public ResponseEntity<?> viewCommuteRequests(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        return ResponseEntity.badRequest().body("null");
    }

    // 근태 요청 처리
    @PutMapping("/manage-requests")
    public ResponseEntity<?> manageCommuteRequests(@SessionAttribute("employee") SessionEmployeeDTO sessionEmployeeDTO) {

        return ResponseEntity.badRequest().body("null");
    }


}
