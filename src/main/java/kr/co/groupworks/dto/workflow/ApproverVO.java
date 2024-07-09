package kr.co.groupworks.dto.workflow;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter @Setter
@ToString
@Accessors(chain = true)
public class ApproverVO {
    @NotNull
    // 2, 결재요청 fk
    private int workFlowId;

    @NotNull
    // 3, 결재 순서
    private int sequenceNum;

    @NotNull
    // 4, 결재자, 협조자, 참조자 구분
    private int approverType;

    @NotNull
    // 5, 결재자 사원 pk
    private int employeeId;

    @NotEmpty @NotNull
    // 6, 결재자 명
    private String approverName;

    @NotEmpty @NotNull
    // 7, 결재자 직급
    private String approverRank;

    @NotEmpty @NotNull
    // 8, 결재자 소속
    private String department;

    // 최종 결재자 정보
    private ApproverVO finalApprover;
}