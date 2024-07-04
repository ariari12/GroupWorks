package kr.co.groupworks.dto.workflow;

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
    // 2, 결재요청 fk
    private int workFlowId;
    // 3, 결재 순서
    private int sequenceNum;
    // 4, 결재자, 협조자, 참조자 구분
    private int approverType;
    // 5, 결재자 사원 pk
    private String employeeId;
    // 6, 결재자 명
    private String approverName;
    // 7, 결재자 직급
    private String approverRank;
    // 8, 결재자 소속
    private String department;
    // 9, 결재 방식
    private int approvalMethod;
    // 10, 결재 승인 일자
    private String approvalDate;
    // 11, 승인 여부
    private Boolean approval;
}
