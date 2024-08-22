package kr.co.groupworks.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Entity
@Table(name="vacation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Vacation extends Calendar{

    // 승인하는 사람
    @Embedded
    private Approver approver;
    // 승인 상태
    @Enumerated(EnumType.STRING)
    private VacationStatus status;
    //휴가 종류
    @Enumerated(EnumType.STRING)
    @Column(name = "vacation_type")
    private VacationType vacationType;
    // 오전,오후
    @Enumerated(EnumType.STRING)
    @Column(name = "am_pm")
    private AmPm amPm;

    @Column(name = "used_vacation")
    private double usedVacation;

    public Vacation updateVacation(
            VacationType vacationType, String contents, String title, String startDate, String endDate) {

        this.vacationType = vacationType;
        this.contents = contents;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;

        return this;
    }

    public void approvalStatus(VacationStatus status, Approver approver, Long employeeId) {
        this.status=status;
        this.approver=approver;
    }

    public void updateUsedVacation(LocalDate startDate, LocalDate endDate) {
        usedVacation += (int) (ChronoUnit.DAYS.between(startDate, endDate)+1);
    }

    public void updateHalfDaysUsed() {
        usedVacation += 0.5;
    }
}
