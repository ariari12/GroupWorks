package kr.co.groupworks.dto.ljm.vo;

import kr.co.groupworks.dto.ljm.WorkFlowType;
import kr.co.groupworks.entity.ljm.WorkFlowEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.format.DateTimeFormatter;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class WorkflowListVO {
    /* 목록 조회 시에만 사용되는 DTO <- Entity 변화 */

    // 1, 결재 요청 pk
    private long id;

    // 2, 기안자 사원번호 fk
    private long employeeId;
    // 3, 기안자 직급
    private String employeeRank;
    // 4, 기안자 소속번호
    private long departmentId;
    // 5, 기안자 소속명
    private String department;
    // 6, 기안자 명
    private String employeeName;

    // 7, 결재 기안 코드
    private String code;
    // 8, 결재 요청 분류
    private String workFlowType;
    // 9, 결재 명
    private String title;
    // 10, 결재 요청 일
    private String draftDate;
    // 11, 최종 결재 일
    private String approvalDate;

    // 12, 최종 결재자 직급
    private String finalApprovalRank;
    // 13, 최종 결재자 소속
    private String finalApprovalDepartment;
    // 14, 최종 결재자 명
    private String finalApprovalName;

    // 15, 결재 인원
    private int approverCount;
    // 16, 승인된 수
    private int approvalCount;
    // 17, 최종 승인여부
    private int status;

    public WorkflowListVO(WorkFlowEntity workFlowEntity) {
        String drftDate = workFlowEntity.getDraftDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String approvalDate = null;

        if(workFlowEntity.getApprovalDate() != null) {
            approvalDate = workFlowEntity.getApprovalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }

        this
                // pk
                .setId(workFlowEntity.getId())
                // fk 기안자
                .setEmployeeId(workFlowEntity.getEmployeeId())
                .setEmployeeRank(workFlowEntity.getEmployeeRank())
                .setDepartmentId(workFlowEntity.getDepartmentId())
                .setDepartment(workFlowEntity.getDepartment())
                .setEmployeeName(workFlowEntity.getEmployeeName())
                // 결재내용
                .setCode(workFlowEntity.getCode())
                .setWorkFlowType(WorkFlowType.getMatch(workFlowEntity.getWorkFlowType()))
                .setTitle(workFlowEntity.getTitle())
                .setDraftDate(drftDate)
                .setApprovalDate(approvalDate)
                // 최종 결재자
                .setFinalApprovalRank(workFlowEntity.getFinalApprovalRank())
                .setFinalApprovalDepartment(workFlowEntity.getFinalApprovalDepartment())
                .setFinalApprovalName(workFlowEntity.getFinalApprovalName())
                // 결재 상황
                .setApproverCount(workFlowEntity.getApproverCount())
                .setApprovalCount(workFlowEntity.getApprovalCount())
                .setStatus(workFlowEntity.getStatus())
                ;
    }
}
