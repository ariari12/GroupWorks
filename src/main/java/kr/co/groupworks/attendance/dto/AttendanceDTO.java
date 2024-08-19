package kr.co.groupworks.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTO {
    private Long attendanceId;
    private Long employeeId;
    private LocalDateTime date;
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;
    private int workHours;
    private int overtimeHours;
    private String status;

    // 포맷된 날짜
    public String getFormattedDate() {
        return date != null ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }

    // 포맷된 출근 시간
    public String getFormattedStartTime() {
        return clockInTime != null ? clockInTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }

    // 포맷된 퇴근 시간
    public String getFormattedEndTime() {
        return clockOutTime != null ? clockOutTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }

    // 더미 데이터 예시 (근무시간 및 초과근무시간)
    public String getWorkHours() {
        if (clockInTime == null || clockOutTime == null) {
            return "0시간 0분";
        }
        // 시간과 분으로 나누기
        int hours = workHours / 60;
        int minutes = workHours % 60;
        return String.format("%d시간 %d분", hours, minutes);
    }

    public String getOvertimeHours() {
        if (clockInTime == null || clockOutTime == null) {
            return "0시간 0분";
        }

        Duration workDuration = Duration.between(clockInTime, clockOutTime);
        long totalMinutes = workDuration.toMinutes();

        // 표준 근무 시간 (9시간) = 540분
        long standardMinutes = 540;
        long overtimeMinutes = Math.max(totalMinutes - standardMinutes, 0);

        int hours = (int) (overtimeMinutes / 60);
        int minutes = (int) (overtimeMinutes % 60);

        return String.format("%d시간 %d분", hours, minutes);
    }

}
