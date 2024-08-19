package kr.co.groupworks.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String approver;
    private LocalDateTime approvalDate;
}
