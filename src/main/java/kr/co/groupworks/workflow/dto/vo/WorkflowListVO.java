package kr.co.groupworks.workflow.dto.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.workflow.dto.WorkFlowType;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;
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

    @Schema(description = "결재요청 고유 식별자", example = "1")
    // 1, 결재 요청 pk
    private long id;

    @Schema(description = "사원 고유 식별자", example = "1")
    // 2, 기안자 사원번호 fk
    private long employeeId;

    @Schema(description = "사원 직급", example = "부장")
    // 3, 기안자 직급
    private String employeeRank;

    @Schema(description = "사원 소속 고유 식별자", example = "1")
    // 4, 기안자 소속번호
    private long departmentId;

    @Schema(description = "사원 소속 부서 명", example = "마케팅 부서")
    // 5, 기안자 소속명
    private String department;

    @Schema(description = "사원 명", example = "홍길동")
    // 6, 기안자 명
    private String employeeName;

    @Schema(description = "결재 기안 코드", example = "0000-01-202408171633")
    // 7, 결재 기안 코드
    private String code;

    @Schema(description = "결재요청 구분", example = "업무 결재")
    // 8, 결재 요청 분류
    private String workFlowType;

    @Schema(description = "결재 제목", example = "OO 관련하여 서류내용 결재 부탁드립니다.")
    // 9, 결재 명
    private String title;

    @Schema(description = "결재 요청 일자", example = "2024-08-17T16:44")
    // 10, 결재 요청 일
    private String draftDate;

    @Schema(description = "최종 결재 처리 일자", example = "2024-08-29T16:49")
    // 11, 최종 결재 일
    private String approvalDate;

    @Schema(description = "최종 결재자 직급", example = "대표이사")
    // 12, 최종 결재자 직급
    private String finalApprovalRank;

    @Schema(description = "최종 결재자 소속 명", example = "임원")
    // 13, 최종 결재자 소속
    private String finalApprovalDepartment;

    @Schema(description = "최종 결재자 명", example = "홍길동")
    // 14, 최종 결재자 명
    private String finalApprovalName;

    @Schema(description = "결재 인원", example = "3")
    // 15, 결재 인원
    private int approverCount;

    @Schema(description = "결재 승인된 수", example = "2")
    // 16, 승인된 수
    private int approvalCount;

    @Schema(description = "최종 승인 여부(0|3:진행, 1: 승인, 2:반려)", example = "3")
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
