package kr.co.groupworks.attendance.entity;

import jakarta.persistence.*;
import kr.co.groupworks.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance_modification")
public class AttendanceModification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modify_id")
    private Long modifyId;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "requested_clock_in")
    private LocalDateTime requestedClockIn;

    @Column(name = "requested_clock_out")
    private LocalDateTime requestedClockOut;

    @Column(nullable = false)
    private String reason;

    @Column(name = "work_hours")
    private int workHours;

    @Column(name = "overtime_hours")
    private int overtimeHours;

    private String status;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private Employee approver;

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    public String getEmployeeName() {
        return attendance != null && attendance.getEmployee() != null
                ? attendance.getEmployee().getEmployeeName()
                : "";
    }

    public void updateStatus (String status, Employee approver) {
        this.status = status;
        this.approver = approver;
        this.approvalDate = LocalDateTime.now();
    }
}
