package kr.co.groupworks.dto.workflow;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApproverDTO {

    // 1, 결재자 pk
    private int id;

    @NotEmpty @NotNull
    // 2, 결재요청 fk
    private int workFlowId;

    @NotEmpty @NotNull
    // 3, 결재 순서
    private int sequenceNum;

    @NotEmpty @NotNull
    // 4, 1:결재자, 2:협조자, 3:참조자 구분
    private int approverType;

    @NotEmpty @NotNull
    // 5, 결재자 사원 fk
    private int employeeId;

    @NotEmpty @NotNull
    // 6, 결재자 명
    private String approverName;

    // 7, 결재자 직급
    private String approverRank;

    // 8, 결재자 소속 String
    private String department;

    // 9, 결재 방식 Integer
    private int approvalMethodInt;
    // 9, 결재 방식 String
    private String approvalMethodStr;

    // 10, 반려사유/협의안/코멘트
    private String comment;

    // 11, 결재 승인 일자
    private String approvalDate;

    // 12, 승인 여부 Boolean
    private Boolean approval;
    // 12, 승인 여부 String
    private String approvalStr;
}
