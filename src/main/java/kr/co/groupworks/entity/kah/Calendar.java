package kr.co.groupworks.entity.kah;


import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public class Calendar {
    @Id
    @Column(name = "caldendar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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



}
