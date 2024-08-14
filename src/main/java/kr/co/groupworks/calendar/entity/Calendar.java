package kr.co.groupworks.calendar.entity;


import jakarta.persistence.*;
import kr.co.groupworks.BaseEntity;
import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.employee.entity.Employee;
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
public class Calendar extends BaseEntity {
    @Id
    @Column(name = "calendar_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long calendarId;
    // 제목
    protected String title;
    // 내용
    protected String contents;
    // 일정 시작일
    @Column(name = "start_date")
    protected String startDate;
    // 일정 종료일
    @Column(name = "end_date")
    protected String endDate;
    // 사원 외래키
    @ManyToOne
    @JoinColumn(name = "employee_id")
    protected Employee employee;

    // 양방향 관계 (조회용)
    @OneToMany(mappedBy = "calendar",cascade = CascadeType.ALL, orphanRemoval = true)
    protected final List<CalendarAttachment> attachmentList = new ArrayList<>();

    public void updateCalendar(CalendarFormDTO calendarFormDTO) {
        this.title = calendarFormDTO.getTitle();
        this.contents = calendarFormDTO.getContents();
        this.startDate = calendarFormDTO.getStartDate();
        this.endDate = calendarFormDTO.getEndDate();
    }
}
