package kr.co.groupworks.entity.kah;


import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue
    private Long calendarId;
    // 제목
    private String title;
    // 내용
    private String contents;
    // 일정 시작일
    private String startDate;
    // 일정 종료일
    private String endDate;

    // 사원 외래키
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    //휴가 외래키
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacation> vacationList = new ArrayList<>();

}
