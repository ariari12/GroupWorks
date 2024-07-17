package kr.co.groupworks.entity.kah;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue
    // 내용
    private Long NotificationId;
    // 제목
    private String title;
    // 내용
    private String contents;
    // 알람 생성일
    private String createdDate;
    // 알람 종류
    private String type;
    // 알람 링크 눌렀을 경우 페이지 이동
    private String url;
    // 알람 확인 여부
    private boolean isRead;

    // 사원 외래키
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
