package kr.co.groupworks.entity.cis;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "employee")
@Builder @ToString
public class Employee {
    @Id
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    @Column(name = "employee_pw", nullable = false)
    private String employeePW;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "rank_id")
    private Integer rankId;

    @Column(name = "rank_name")
    private String rankName;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "supervisor_id")
    private Integer supervisorId;
}