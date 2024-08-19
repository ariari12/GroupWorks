package kr.co.groupworks.calendar.entity;

import jakarta.persistence.*;
import kr.co.groupworks.common.BaseEntity;
import kr.co.groupworks.employee.entity.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private double totalAnnual;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    //캡슐화 오직 매퍼메서드를 제외하고 이곳을 통해서만 초기화시키고싶음
    public static VacationHistory createFromEmployee(Employee employee) {
        VacationHistory vacationHistory = new VacationHistory();
        vacationHistory.employee = employee;
        vacationHistory.totalAnnual = 25;
        return vacationHistory;
    }

    public void updateAnnual(double annual){
        annualDaysUsed +=annual;
        totalAnnual -= annual;
    }

    public void updateAnnual(double annual){
        annualDaysUsed +=annual;
        totalAnnual -= annual;
    }

    public void updateSick(int annual){
        sickDaysUsed += annual;
    }

    public void updateOther(int annual){
        otherDaysUsed += annual;
    }

    public void resetAnnual(){
        this.annualDaysUsed = 0;
        this.sickDaysUsed = 0;
        this.otherDaysUsed=0;
        this.totalAnnual = 25;
    };
}
