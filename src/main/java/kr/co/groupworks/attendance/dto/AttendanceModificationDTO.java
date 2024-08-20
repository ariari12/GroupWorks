package kr.co.groupworks.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceModificationDTO {
    private Long modifyId;
    private Long employeeId;
    private Long attendanceId;
    private LocalDateTime requestDate;
    private LocalDateTime requestedClockIn;
    private LocalDateTime requestedClockOut;
    private String reason;
    private int workHours;
    private int overtimeHours;
    private String status;
    private LocalDateTime approvalDate;
    private String employeeName;

    // 포맷된 요청 날짜
    public String getFormattedRequestDate() {
        return requestDate != null ? requestDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
    }

    // 포맷된 요청된 출근 시간
    public String getFormattedRequestedClockIn() {
        return requestedClockIn != null ? requestedClockIn.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }

    // 포맷된 요청된 퇴근 시간
    public String getFormattedRequestedClockOut() {
        return requestedClockOut != null ? requestedClockOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
    }

    // 근무시간
    public String getWorkHour() {
        if (requestedClockIn == null || requestedClockOut == null) {
            return "0시간 0분";
        }
        // 시간과 분으로 나누기
        int hours = workHours / 60;
        int minutes = workHours % 60;
        return String.format("%d시간 %d분", hours, minutes);
    }

    // 초과근무시간
    public String getOvertimeHour() {
        if (requestedClockIn == null || requestedClockOut == null) {
            return "0시간 0분";
        }

        Duration workDuration = Duration.between(requestedClockIn, requestedClockOut);
        long totalMinutes = workDuration.toMinutes();

        // 표준 근무 시간 (9시간) = 540분
        long standardMinutes = 540;
        long overtimeMinutes = Math.max(totalMinutes - standardMinutes, 0);

        int hours = (int) (overtimeMinutes / 60);
        int minutes = (int) (overtimeMinutes % 60);

        return String.format("%d시간 %d분", hours, minutes);
    }

}
