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
    // 1, 결재 요청 pk
    private long id;

    // 2, 기안자 사원번호 fk
    private long employeeId;
    // 3, 기안자 이메일
    private String email;
    // 4, 기안자 연락처
    private String phone;
    // 4, 기안자 직급
    private String employeeRank;
    // 5, 기안자 소속번호
    private long departmentId;
    // 6, 기안자 소속명
    private String department;
    // 7, 기안자 명
    private String employeeName;

    // 8, 결재 기안 코드
    private String code;
    // 9, 결재 요청 분류
    private int workFlowType;
    // 10, 결재 명
    private String title;

    // 11, 결재 내용
    private String description;
    // 12, 결재 요청 일
    private LocalDateTime draftDate;
    // 13, 최종 결재 일
    private LocalDateTime approvalDate;

    // 14, 최종 결재자 직급
    private String finalApprovalRank;
    // 15, 최종 결재자 소속
    private String finalApprovalDepartment;
    // 16, 최종 결재자 명
    private String finalApprovalName;

    // 17, 결재 인원
    private int approverCount;
    // 18, 승인된 수
    private int approvalCount;
    // 19, 비용 청구
    private long cost;
    // 20, 최종 승인여부
    private int status;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "appover_id")
    private List<ApproverEntity> approvers;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "af_id")
    private List<AttachmentFileEntity> attachmentFiles;
}
