package kr.co.groupworks.entity.ljm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "workflow")
@Accessors(chain = true)
@NoArgsConstructor
public class WorkFlowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 1, 결재 요청 pk
    private int id;

    // 2, 기안자 사원번호 fk
    private int employeeId;
    // 3, 기안자 이메일
    private String email;
    // 4, 기안자 직급
    private String employeeRank;
    // 5, 기안자 소속번호
    private Integer departmentId;
    // 6, 기안자 소속명
    private String department;
    // 7, 기안자 명
    private String employeeName;

    // 8, 결재 기안 코드
    private String code;
    // 9, 결재 요청 분류
    private String workFlowType;
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
    private int cost;
    // 20, 최종 승인여부
    private String status;

    @OneToMany
    @JoinColumn(name = "appover_id")
    private List<ApproverEntity> approvers;

    @OneToMany
    @JoinColumn(name = "af_id")
    private List<AttachmentFileEntity> attachmentFiles;
}
