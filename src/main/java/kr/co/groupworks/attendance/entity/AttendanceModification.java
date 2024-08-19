package kr.co.groupworks.attendance.entity;

import jakarta.persistence.*;
import kr.co.groupworks.employee.entity.Employee;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
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

    @Column(nullable = false)
    private String approver;

    @Column(name = "approval_date")
    private LocalDateTime approvalDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;
}
