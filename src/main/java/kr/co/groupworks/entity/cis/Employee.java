package kr.co.groupworks.entity.cis;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "employee")
@Builder @ToString(exclude = "department")
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

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
    private Long salary;

    @Column(name = "supervisor_id")
    private Long supervisorId;

    @Column(name = "annual_days_used")
    private double annualDaysUsed;

    @Column(name = "sick_days_used")
    private int sickDaysUsed;

    @Column(name = "other_days_used")
    private int otherDaysUsed;

    public void updateAnnualDaysUsed(LocalDate startDate, LocalDate endDate) {
        annualDaysUsed += ChronoUnit.DAYS.between(startDate, endDate)+1;
    }

    public void updateSickDaysUsed(LocalDate startDate, LocalDate endDate) {
        sickDaysUsed += ChronoUnit.DAYS.between(startDate, endDate)+1;
    }

    public void updateOtherDaysUsed(LocalDate startDate, LocalDate endDate) {
        otherDaysUsed += ChronoUnit.DAYS.between(startDate, endDate)+1;
    }
}