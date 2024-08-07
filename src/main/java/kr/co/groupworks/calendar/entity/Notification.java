package kr.co.groupworks.calendar.entity;

import jakarta.persistence.*;
import kr.co.groupworks.common.BaseEntity;
import kr.co.groupworks.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue
    // 내용
    private Long notificationId;
    // 제목
    private String title;
    // 내용
    private String contents;
    // 알람 생성일
//    @Column(name = "created_date")
//    private String createdDate;
    // 알람 종류
    private String type;
    // 알람 링크 눌렀을 경우 페이지 이동
    private String url;
    // 알람 확인 여부
    @Column(name = "is_read")
    private boolean isRead;

    // 사원 외래키
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
