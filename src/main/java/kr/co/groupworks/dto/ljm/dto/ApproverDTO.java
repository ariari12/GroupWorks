package kr.co.groupworks.dto.ljm.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.entity.ljm.ApproverEntity;
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

    // 1, 결재자 pk
    private long id;

    @NotNull
    // 2, 결재요청 fk
    private long workFlowId;
    @NotNull
    // 3, 결재 순서
    private int sequenceNum;

    @NotNull
    // 4, 1:결재자, 2:협조자, 3:참조자 구분
    private int approverType;
    @NotNull
    // 5, 결재자 사원 fk
    private long employeeId;
    // 6, 결재자 이메일
    private String approverEmail;
    // 7, 결재자 연락처
    private String approverPhone;
    @NotEmpty @NotNull
    // 8, 결재자 명
    private String approverName;
    // 9, 결재자 직급
    private String approverRank;
    // 10, 결재자 소속
    private String department;

    // 11, 결재 방식 1:선결, 2:후결, 3:대결, 4:전결, 5:반려
    private int approvalMethod;
    // 12, 반려사유/협의안/코멘트
    private String comment;
    // 13, 결재 승인 일자
    private String approvalDate;
    // 14, 승인 여부 0:진행, 1:승인, 2:반려
    private int approval;

    public ApproverEntity dtoToEntity() {
        /* approvalDate: 2024-07-12T04:58 */
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$";
        LocalDateTime finalDate = (approvalDate == null || approvalDate.matches(datePattern)) ? null :
                LocalDateTime.parse(approvalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

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
        String datePattern = "yyyy-MM-dd'T'HH:mm";
        String approvalDate = approverEntity.getApprovalDate() == null ? null :
                approverEntity.getApprovalDate().format(DateTimeFormatter.ofPattern(datePattern));

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
