package kr.co.groupworks.entity.yhj.attendance;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance_modification")
public class AttendanceModification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modify_id")
    private Long modifyId;

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;
}
