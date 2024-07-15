package kr.co.groupworks.entity.kah;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue
    private Long vacationId;
    // 요청 휴일 개수
    private String requestedLeaveDays;
    // 클라이언트가 올린 파일이름
    private String fileName;
    // 실제 저장 파일 경로
    private String filePath;
    // 승인 상태
    private String status;
    // 승인하는 사람
    private String approver;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    Calendar calendar;

    @OneToOne
    @JoinColumn(name = "vacation_type_id")
    private VacationType vacationType;
}
