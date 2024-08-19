package kr.co.groupworks.attendance.service;

import kr.co.groupworks.attendance.dto.AttendanceDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface AttendanceService {
    List<AttendanceDTO> getAttendanceByEmployeeIdAndMonth(Long employeeId, YearMonth month);
    boolean isClockedIn(Long employeeId, LocalDate today);
    boolean isClockedOut(Long employeeId, LocalDate today);

    void clockIn(Long employeeId);
    void clockOut(Long employeeId);
}
