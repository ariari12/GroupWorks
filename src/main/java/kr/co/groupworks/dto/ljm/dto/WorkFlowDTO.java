package kr.co.groupworks.dto.ljm.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.entity.ljm.WorkFlowEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter @ToString
@Accessors(chain = true)
@NoArgsConstructor
public class WorkFlowDTO {
    /* 결재요청 정보를 저장하는 DTO -> Entity 변환 */

    // 1, 결재 요청 pk
    private long id;

    // 2, 기안자 사원번호 fk
    private long employeeId;
    // 3, 기안자 이메일
    private String email;
    // 4, 기안자 연락처
    private String phone;
    // 5, 기안자 직급
    private String employeeRank;
    // 6, 기안자 소속번호
    private long departmentId;
    // 7, 기안자 소속명
    private String department;
    // 8, 기안자 명
    private String employeeName;

    @NotEmpty @NotNull
    // 9, 결재 기안 코드
    private String code;
    @NotNull
    // 10, 결재 요청 분류
    private int workFlowType;
    @NotEmpty @NotNull
    // 11, 결재 명
    private String title;
    @NotEmpty @NotNull
    // 12, 결재 내용
    private String description;
    @NotEmpty @NotNull
    // 13, 결재 요청 일
    private String draftDate;
    // 14, 최종 결재 일
    private String approvalDate;

    // 15, 최종 결재자 직급
    private String finalApprovalRank;
    // 16, 최종 결재자 소속
    private String finalApprovalDepartment;
    // 17, 최종 결재자 명
    private String finalApprovalName;

    @NotNull @Min(value = 1)
    // 18, 결재 인원
    private int approverCount;
    // 19, 승인된 수
    private int approvalCount;
    // 20, 비용 청구
    private long cost;
    // 21, 최종 승인여부 0:진행, 1:승인, 2:반려
    private int status;

    private List<ApproverDTO> approvers;
    private List<AttachmentFileDTO> attachmentFiles;

    public WorkFlowEntity dtoToEntity() {
        // 2024-07-16T:07:32, 2024-07-12T04:58
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$";

        LocalDateTime finalDate = (approvalDate == null || approvalDate.matches(datePattern)) ? null :
                LocalDateTime.parse(approvalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));


        return WorkFlowEntity.builder()
                .id(id)
                /* 기안자 */
                .employeeId(employeeId)
                .email(email)
                .phone(phone)
                .employeeRank(employeeRank)
                .departmentId(departmentId)
                .department(department)
                .employeeName(employeeName)
                /* 결재 정보 */
                .code(code)
                .workFlowType(workFlowType)
                .title(title)
                .description(description)
                .cost(cost)
                .draftDate(LocalDateTime.parse(draftDate,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")))
                /* 최종 결재자 정보 */
                .approvalDate(finalDate)
                .finalApprovalRank(finalApprovalRank)
                .finalApprovalDepartment(finalApprovalDepartment)
                .finalApprovalName(finalApprovalName)
                /* 결재 상황 */
                .approverCount(approverCount)
                .approvalCount(approvalCount)
                .status(status)
                .approvers(approvers != null ? approvers.stream().map(ApproverDTO::dtoToEntity).toList() : null)
                .attachmentFiles(attachmentFiles != null ? attachmentFiles.stream().map(AttachmentFileDTO::dtoToEntity).toList() : null)
                .build();
    }

    public static WorkFlowDTO entityToDto(WorkFlowEntity workFlowEntity) {
        String draftDate = workFlowEntity.getDraftDate() == null ? null :
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").format(workFlowEntity.getDraftDate());
        String approvalDate = workFlowEntity.getApprovalDate() == null ? null :
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").format(workFlowEntity.getApprovalDate());

        return new WorkFlowDTO()
                .setId(workFlowEntity.getId())

                .setEmployeeId(workFlowEntity.getEmployeeId())
                .setEmail(workFlowEntity.getEmail())
                .setPhone(workFlowEntity.getPhone())
                .setEmployeeRank(workFlowEntity.getEmployeeRank())
                .setDepartmentId(workFlowEntity.getDepartmentId())
                .setDepartment(workFlowEntity.getDepartment())
                .setEmployeeName(workFlowEntity.getEmployeeName())

                .setCode(workFlowEntity.getCode())
                .setWorkFlowType(workFlowEntity.getWorkFlowType())
                .setTitle(workFlowEntity.getTitle())
                .setDescription(workFlowEntity.getDescription())
                .setCost(workFlowEntity.getCost())
                .setDraftDate(draftDate)
                .setApprovalDate(approvalDate)

                .setFinalApprovalRank(workFlowEntity.getFinalApprovalRank())
                .setFinalApprovalDepartment(workFlowEntity.getFinalApprovalDepartment())
                .setFinalApprovalName(workFlowEntity.getFinalApprovalName())

                .setApproverCount(workFlowEntity.getApproverCount())
                .setApprovalCount(workFlowEntity.getApprovalCount())
                .setStatus(workFlowEntity.getStatus())
                .setApprovers(workFlowEntity.getApprovers() != null ? workFlowEntity.getApprovers().stream().map(ApproverDTO::entityToDto).toList() : null)
                .setAttachmentFiles(workFlowEntity.getAttachmentFiles() != null ? workFlowEntity.getAttachmentFiles().stream().map(AttachmentFileDTO::entityToDto).toList() : null)
                ;
    }
}
