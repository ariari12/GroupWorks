package kr.co.groupworks.calendar.entity;

import jakarta.persistence.*;
import kr.co.groupworks.BaseEntity;
import kr.co.groupworks.entity.cis.Employee;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="vacation_history")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VacationHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_history_id")
    private Long vacationHistoryId;

    @Column(name = "annual_days_used")
    private double annualDaysUsed;

    @Column(name = "sick_days_used")
    private int sickDaysUsed;

    @Column(name = "other_days_used")
    private int otherDaysUsed;

    @Column(name = "total_annual")
    private int totalAnnual;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public void updateAnnualDaysUsed(LocalDate startDate, LocalDate endDate) {
        annualDaysUsed += ChronoUnit.DAYS.between(startDate, endDate)+1;
    }

    public void updateSickDaysUsed(LocalDate startDate, LocalDate endDate) {
        sickDaysUsed += ChronoUnit.DAYS.between(startDate, endDate)+1;
    }

    public void updateOtherDaysUsed(LocalDate startDate, LocalDate endDate) {
        otherDaysUsed += ChronoUnit.DAYS.between(startDate, endDate)+1;
    }
    public void updateHalfDaysUsed() {
        annualDaysUsed += 0.5;
    }

    //캡슐화 오직 매퍼메서드를 제외하고 이곳을 통해서만 초기화시키고싶음
    public static VacationHistory createFromEmployee(Employee employee) {
        VacationHistory vacationHistory = new VacationHistory();
        vacationHistory.employee = employee;
        vacationHistory.totalAnnual = 25;
        return vacationHistory;
    }


}
