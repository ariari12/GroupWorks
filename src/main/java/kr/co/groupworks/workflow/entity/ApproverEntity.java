package kr.co.groupworks.workflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Table(name = "approver")
@AllArgsConstructor
@NoArgsConstructor
public class ApproverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approver_id")
    // 1, 결재자 pk
    private long id;

    @Column(name = "workflow_id")
    // 2, 결재요청 fk
    private long workFlowId;
    // 3, 결재 순서
    private int sequenceNum;

    // 4, 1:결재자, 2:협조자, 3:참조자 구분
    private int approverType;
    // 5, 결재자 사원 fk
    private long employeeId;
    // 6, 결재자 이메일
    private String approverEmail;
    // 7, 결재자 연락처
    private String approverPhone;
    // 8, 결재자 명
    private String approverName;
    // 9, 결재자 직급
    private String approverRank;
    // 10, 결재자 소속
    private String department;

    // 11, 결재 방식 1:선결, 2:후결, 3:대결, 4:전결, 5:반려
    private int approvalMethod;
    // 12, 반려사유/협의안/코멘트
    private String comment;
    // 13, 결재 승인 일자
    private LocalDateTime approvalDate;
    // 14, 승인 여부 0:진행, 1:승인, 2:반려, 3:전결
    private int approval;
}
