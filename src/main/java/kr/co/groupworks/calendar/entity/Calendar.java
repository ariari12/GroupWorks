package kr.co.groupworks.calendar.entity;


import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "calendar")
@ToString(exclude = {"attachmentList"})
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public class Calendar {
    @Id
    @Column(name = "calendar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendarId;
    // 제목
    private String title;
    // 내용
    private String contents;
    // 일정 시작일
    @Column(name = "start_date")
    private String startDate;
    // 일정 종료일
    @Column(name = "end_date")
    private String endDate;
    @CreatedDate
    @Column(updatable = false, name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    // 사원 외래키
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // 양방향 관계 (조회용)
    @OneToMany(mappedBy = "calendar",cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CalendarAttachment> attachmentList = new ArrayList<>();



}
