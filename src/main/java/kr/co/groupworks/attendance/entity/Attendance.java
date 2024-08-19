package kr.co.groupworks.attendance.entity;

import jakarta.persistence.*;
import kr.co.groupworks.employee.entity.Employee;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "clock_in_time")
    private LocalDateTime clockInTime;

    @Column(name = "clock_out_time")
    private LocalDateTime clockOutTime;

    @Column(name = "work_hours")
    private int workHours;

    @Column(name = "overtime_hours")
    private int overtimeHours;

    @Column(name = "status")
    private String status;


    public void updateAttendance (LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
        this.status = "\uD83C\uDF1F 출퇴근 완료"; // 🌟

        Duration duration = Duration.between(this.clockInTime, clockOutTime);
        int workTime = (int) duration.toMinutes();
        this.workHours = workTime;
        this.overtimeHours = workTime >= 540 ? workTime - 540 : 0;
    }
}
