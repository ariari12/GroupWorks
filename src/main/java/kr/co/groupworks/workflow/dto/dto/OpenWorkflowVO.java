package kr.co.groupworks.workflow.dto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Getter
@ToString
public class OpenWorkflowVO {
    /* 사원에 따른 결재자 OpenApi 정보 */

    @Schema(description = "결재요청 번호", defaultValue = "1")
    // 1, 결재 요청 pk
    private long id;

    @Schema(description = "기안자 사원번호", defaultValue = "1")
    // 2, 기안자 사원번호 fk
    private long employeeId;
    @Schema(description = "기안자 이메일", defaultValue = "example@example.com")
    // 3, 기안자 이메일
    private String email;
    @Schema(description = "기안자 연락처", defaultValue = "010-1234-1234")
    // 4, 기안자 연락처
    private String phone;
    @Schema(description = "기안자 직급", defaultValue = "사원")
    // 5, 기안자 직급
    private String employeeRank;
    @Schema(description = "기안자 소속부서 번호", defaultValue = "1")
    // 6, 기안자 소속번호
    private long departmentId;
    @Schema(description = "기안자 소속부서 명", defaultValue = "전산팀")
    // 7, 기안자 소속명
    private String department;
    @Schema(description = "기안자 명", defaultValue = "홍길동")
    // 8, 기안자 명
    private String employeeName;

    @NotEmpty @NotNull
    @Schema(description = "결재 기안코드: 사원번호+부서번호 - 기안구분 - 기안년월일", defaultValue = "12-1-19991111")
    // 9, 결재 기안 코드
    private String code;
    @NotNull @Min(value = 1) @Max(value = 6)
    @Schema(description = "결재요청 구분(1업부, 2재무, 3예산, 4구매, 5보고, 6특별)", defaultValue = "12-1-19991111")
    // 10, 결재 요청 분류
    private int workFlowType;
    @NotEmpty @NotNull
    @Schema(description = "결재요청 제목", defaultValue = "example")
    // 11, 결재 명
    private String title;
    @NotEmpty @NotNull
    @Schema(description = "결재요청 내용", defaultValue = "contents")
    // 12, 결재 내용
    private String description;
    @NotEmpty @NotNull @Length(max = 2000)
    @Schema(description = "결재 승인 일자", defaultValue = "1999-12-31T24:24")
    // 13, 결재 요청 일
    private String draftDate;
    @Schema(description = "최종 결재 일자", defaultValue = "1999-12-31T24:24")
    // 14, 최종 결재 일
    private String approvalDate;

    @Schema(description = "최종 결재자 사원번호", defaultValue = "1")
    // 15, 최종 결재자 사원번호
    private long finalEmployeeId;
    @Schema(description = "최종 결재자 직급", defaultValue = "이사")
    // 16, 최종 결재자 직급
    private String finalApprovalRank;
    @Schema(description = "최종 결재자 소속명", defaultValue = "임원")
    // 17, 최종 결재자 소속
    private String finalApprovalDepartment;
    @Schema(description = "최종 결재자 명", defaultValue = "홍길동")
    // 18, 최종 결재자 명
    private String finalApprovalName;

    @NotNull @Min(value = 1) @Max(value = 6)
    @Schema(description = "결재 인원(최소인원: 1, 최대인원: 5))", defaultValue = "5")
    // 19, 결재 인원
    private int approverCount;
    @Schema(description = "결재된 수(최소: 0, 최대: 5)", defaultValue = "2")
    // 20, 승인된 수
    private int approvalCount;
    @Schema(description = "비용 청구", defaultValue = "10000")
    // 21, 비용 청구
    private long cost;
    @Schema(description = "최종 승인여부(0:발송, 1:승인, 2:반려, 3:진행)", defaultValue = "3")
    // 22, 최종 승인여부 0:발송, 1:승인, 2:반려, 3:진행
    private int status;

    private List<ApproverDTO> approvers;
    private List<Map<Long, String>> attachmentFiles;

    public OpenWorkflowVO(WorkFlowEntity w) {
        String format = "yyyy-MM-dd'T'HH:mm:ss";
        this.id = w.getId();
        this.employeeId = w.getEmployeeId();
        this.email = w.getEmail();
        this.phone = w.getPhone();
        this.employeeRank = w.getEmployeeRank();
        this.departmentId = w.getDepartmentId();
        this.department = w.getDepartment();
        this.employeeName = w.getEmployeeName();
        this.code = w.getCode();
        this.workFlowType = w.getWorkFlowType();
        this.title = w.getTitle();
        this.description = w.getDescription();
        this.draftDate = w.getDraftDate().format(DateTimeFormatter.ofPattern(format));
        this.approvalDate = w.getApprovalDate() != null ?
                w.getApprovalDate().format(DateTimeFormatter.ofPattern(format)) : null;
        this.finalApprovalRank = w.getFinalApprovalRank();
        this.finalApprovalDepartment = w.getFinalApprovalDepartment();
        this.finalApprovalName = w.getFinalApprovalName();
        this.approverCount = w.getApproverCount();
        this.approvalCount = w.getApprovalCount();
        this.cost = w.getCost();
        this.status = w.getStatus();
        this.approvers = w.getApprovers().stream().map(ApproverDTO::entityToDto).toList();
        this.attachmentFiles = w.getAttachmentFiles().stream().map(f -> Map.of(f.getId(), f.getFileName())).toList();
    }
}
