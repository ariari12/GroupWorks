package kr.co.groupworks.entity.kah;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue
    private Long vacationId;
    // 요청 휴일 개수
    private int requestedLeaveDays;
    // 클라이언트가 올린 파일이름
    private String fileName;
    // 실제 저장 파일 경로
    private String filePath;
    // 승인 상태
    private String status;
    // 승인하는 사람
    private String approver;
    //휴가 종류
    private String vacationType;
    //최대휴가일
    private int maxLeave;
    //휴가에 대한 정보
    private String information;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    Calendar calendar;
}
