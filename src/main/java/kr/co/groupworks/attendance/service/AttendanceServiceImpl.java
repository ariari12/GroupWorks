package kr.co.groupworks.attendance.service;

import kr.co.groupworks.attendance.dto.AttendanceDTO;
import kr.co.groupworks.attendance.entity.Attendance;
import kr.co.groupworks.attendance.repository.AttendanceRepository;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<AttendanceDTO> getAttendanceByEmployeeIdAndMonth(Long employeeId, YearMonth month) {
        // 월의 첫 날과 마지막 날을 계산합니다.
        LocalDate startOfMonth = month.atDay(1);
        LocalDate endOfMonth = month.atEndOfMonth();

        // LocalDate를 LocalDateTime으로 변환합니다.
        LocalDateTime startDateTime = startOfMonth.atStartOfDay();
        LocalDateTime endDateTime = endOfMonth.atTime(23, 59, 59, 999999999); // 하루의 끝


        List<Attendance> attendances = attendanceRepository.findAllByEmployee_EmployeeIdAndDateBetween(employeeId, startDateTime, endDateTime);

        return attendances.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isClockedIn(Long employeeId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        Optional<Attendance> attendance = attendanceRepository.findClockInRecordForToday(employeeId, startOfDay, endOfDay);
        return attendance.isPresent();
    }

    @Override
    public boolean isClockedOut(Long employeeId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59, 999999999);

        Optional<Attendance> attendance = attendanceRepository.findClockOutRecordForToday(employeeId, startOfDay, endOfDay);
        return attendance.isPresent();
    }

    @Override
    public void clockIn(Long employeeId) {

        // 사원을 못찾았을때.
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 직원입니다."));

        // 출근기록 확인.
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX);
        boolean isAlreadyClockedIn = attendanceRepository.existsByEmployeeAndClockInTimeBetween(employee, startOfDay, endOfDay);
        if (isAlreadyClockedIn) {
            throw new IllegalStateException("이미 출근 기록이 있습니다.");
        }

        Attendance attendance = new Attendance();
        attendance.setDate(LocalDateTime.now());
        attendance.setClockInTime(LocalDateTime.now());
        attendance.setEmployee(employee);
        attendance.setStatus("\uD83D\uDFE2 출근 완료"); // 이모지 🟢
        attendanceRepository.save(attendance);
    }

    @Override
    public void clockOut(Long employeeId) {
        // 사원을 못찾았을때.
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 직원입니다."));

        // 출근기록 확인.
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(LocalTime.MAX);
        boolean isAlreadyClockedOut = attendanceRepository.existsByEmployeeAndClockOutTimeBetween(employee, startOfDay, endOfDay);
        if (isAlreadyClockedOut) {
            throw new IllegalStateException("이미 퇴근 기록이 있습니다.");
        }

        Attendance attendance = attendanceRepository.findClockInRecordForToday(employeeId, startOfDay, endOfDay).get();
        attendance.updateAttendance(LocalDateTime.now());
    }

    private AttendanceDTO convertToDTO(Attendance attendance) {
        return AttendanceDTO.builder()
                .attendanceId(attendance.getAttendanceId())
                .employeeId(attendance.getEmployee().getEmployeeId())
                .date(attendance.getDate())
                .clockInTime(attendance.getClockInTime())
                .clockOutTime(attendance.getClockOutTime())
                .workHours(attendance.getWorkHours())
                .overtimeHours(attendance.getOvertimeHours())
                .status(attendance.getStatus())
                .build();
    }
}
