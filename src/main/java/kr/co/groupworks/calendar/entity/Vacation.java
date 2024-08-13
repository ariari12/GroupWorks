package kr.co.groupworks.calendar.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name="vacation")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Vacation extends Calendar{

    // 승인하는 사람
    private String approver;
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

    public Vacation updateVacation(
            VacationType vacationType, String contents, String title, String startDate, String endDate) {

        this.vacationType = vacationType;
        this.contents = contents;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;

        return this;
    }

    public void approvalStatus(VacationStatus status) {
        this.status=status;
    }
}
