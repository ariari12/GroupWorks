package kr.co.groupworks.entity.ljm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "approver")
@Accessors(chain = true)
@NoArgsConstructor
public class ApproverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appover_id")
    // 1, 결재자 pk
    private int id;

    // 2, 결재요청 fk
    private int workFlowId;

    // 3, 결재 순서
    private int sequenceNum;

    // 4, 1:결재자, 2:협조자, 3:참조자 구분
    private int approverType;
    // 5, 결재자 사원 fk
    private int employeeId;
    // 6, 결재자 명
    private String approverName;
    // 7, 결재자 직급
    private String approverRank;
    // 8, 결재자 소속 String
    private String department;

    // 9, 결재 방식 Integer
    private int approvalMethodInt;
    // 10, 반려사유/협의안/코멘트
    private String comment;
    // 11, 결재 승인 일자
    private LocalDateTime approvalDate;
    // 12, 승인 여부 Boolean
    private Boolean approval;
}
