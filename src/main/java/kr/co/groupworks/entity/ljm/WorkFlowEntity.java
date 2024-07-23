package kr.co.groupworks.entity.ljm;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter @ToString
@Table(name = "workflow")
@AllArgsConstructor
@NoArgsConstructor
public class WorkFlowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workflow_id")
    // 1, 결재 요청 pk
    private long id;

    // 2, 기안자 사원번호 fk
    private long employeeId;
    // 3, 기안자 이메일
    private String email;
    // 4, 기안자 연락처
    private String phone;
    // 5, 기안자 직급
    private String employeeRank;
    // 6, 기안자 소속번호
    private long departmentId;
    // 7, 기안자 소속명
    private String department;
    // 8, 기안자 명
    private String employeeName;

    // 9, 결재 기안 코드
    private String code;
    // 10, 결재 요청 분류
    private int workFlowType;
    // 11, 결재 명
    private String title;
    // 12, 결재 내용
    private String description;
    // 13, 결재 요청 일
    private LocalDateTime draftDate;
    // 14, 최종 결재 일
    private LocalDateTime approvalDate;

    // 15, 최종결재자 사원번호
    private long finalEmployeeId;
    // 16, 최종 결재자 직급
    private String finalApprovalRank;
    // 17, 최종 결재자 소속
    private String finalApprovalDepartment;
    // 18, 최종 결재자 명
    private String finalApprovalName;

    // 19, 결재 인원
    private int approverCount;
    // 20, 승인된 수
    private int approvalCount;
    // 21, 비용 청구
    private long cost;
    // 22, 최종 승인여부 0:진행, 1:승인, 2:반려
    private int status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "workflow_id")
    private List<ApproverEntity> approvers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "workflow_id")
    private List<AttachmentFileEntity> attachmentFiles;
}
