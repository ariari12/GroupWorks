package kr.co.groupworks.workflow.dto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.workflow.entity.ApproverEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApproverDTO {
    @Schema(name = "결재자 번호", defaultValue = "1")
    // 1, 결재자 pk
    private long id;

    @NotNull
    @Schema(name = "결재요청 번호", defaultValue = "1")
    // 2, 결재요청 fk
    private long workFlowId;
    @NotNull
    @Schema(name = "결재 순서", defaultValue = "1", description = "1~5")
    // 3, 결재 순서
    private int sequenceNum;

    @NotNull
    @Schema(name = "결재자 구분",  defaultValue = "1", description = "1:결재자, 2:협조자, 3:참조자")
    // 4, 1:결재자, 2:협조자, 3:참조자 구분
    private int approverType;
    @NotNull
    @Schema(name = "결재 사원번호", defaultValue = "1")
    // 5, 결재자 사원 fk
    private long employeeId;
    @Schema(name = "결재자 이메일", defaultValue = "exampl@example.com")
    // 6, 결재자 이메일
    private String approverEmail;
    @Schema(name = "결재자 연락처", defaultValue = "010-1234-1234")
    // 7, 결재자 연락처
    private String approverPhone;
    @NotEmpty @NotNull
    @Schema(name = "결재자 명", defaultValue = "홍길동")
    // 8, 결재자 명
    private String approverName;
    @Schema(name = "결재자 직급", defaultValue = "사원")
    // 9, 결재자 직급
    private String approverRank;
    @Schema(name = "결재자 소속부서", defaultValue = "전산팀")
    // 10, 결재자 소속
    private String department;

    @Schema(name = "결재 방식 구분", defaultValue = "1", description = "1:선결, 2:후결, 3:대결, 4:전결, 5:반려")
    // 11, 결재 방식 1:선결, 2:후결, 3:대결, 4:전결, 5:반려
    private int approvalMethod;
    @Schema(name = "결재 방식 구분", defaultValue = "승인합니다.", description = "반려사유/협의안/코멘트")
    // 12, 반려사유/협의안/코멘트
    private String comment;
    @Schema(name = "결재 승인 일자", defaultValue = "1999-12-31T24:24")
    // 13, 결재 승인 일자
    private String approvalDate;
    @Schema(name = "결재 승인 일자", defaultValue = "1", description = "0:진행, 1:승인, 2:반려, 3:전결")
    // 14, 승인 여부 0:진행, 1:승인, 2:반려, 3:전결
    private int approval;

    public static final String STR_TO_LDT_PATTERN = "yyyy-MM-dd'T'HH:mm";

    public ApproverEntity dtoToEntity() {
        /* approvalDate: 2024-07-12T04:58 */
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$";
        LocalDateTime finalDate = (approvalDate == null || !approvalDate.matches(datePattern)) ? null :
                LocalDateTime.parse(approvalDate, DateTimeFormatter.ofPattern(STR_TO_LDT_PATTERN));

        return ApproverEntity.builder()
                /* 결재 문서 정보 */
                .id(id)
                .workFlowId(workFlowId)
                .sequenceNum(sequenceNum)
                /* 결재자 정보 */
                .approverType(approverType)
                .employeeId(employeeId)
                .approverEmail(approverEmail)
                .approverPhone(approverPhone)
                .approverName(approverName)
                .approverRank(approverRank)
                .department(department)
                /* 결재 정보 */
                .approvalMethod(approvalMethod)
                .comment(comment)
                .approvalDate(finalDate)
                .approval(approval)
                .build();
    }

    public static ApproverDTO entityToDto(ApproverEntity approverEntity) {
        String approvalDate = approverEntity.getApprovalDate() == null ? null :
                approverEntity.getApprovalDate().format(DateTimeFormatter.ofPattern(STR_TO_LDT_PATTERN));

        return new ApproverDTO()
                .setId(approverEntity.getId())
                .setWorkFlowId(approverEntity.getWorkFlowId())
                .setSequenceNum(approverEntity.getSequenceNum())

                .setApproverType(approverEntity.getApproverType())
                .setEmployeeId(approverEntity.getEmployeeId())
                .setApproverEmail(approverEntity.getApproverEmail())
                .setApproverPhone(approverEntity.getApproverPhone())
                .setApproverName(approverEntity.getApproverName())
                .setApproverRank(approverEntity.getApproverRank())
                .setDepartment(approverEntity.getDepartment())

                .setApprovalMethod(approverEntity.getApprovalMethod())
                .setComment(approverEntity.getComment())
                .setApprovalDate(approvalDate)
                .setApproval(approverEntity.getApproval())
                ;
    }
}
