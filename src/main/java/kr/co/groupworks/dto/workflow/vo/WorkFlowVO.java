package kr.co.groupworks.dto.workflow.vo;

import kr.co.groupworks.dto.workflow.WorkFlowType;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter @ToString
@Accessors(chain = true)
@NoArgsConstructor
public class WorkFlowVO {
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

    // 9, 결재 기안 코드
    private String code;
    // 10, 결재 요청 분류
    private String workFlowType;
    // 11, 결재 명
    private String title;
    // 12, 결재 내용
    private String description;
    // 13, 결재 요청 일
    private String draftDate;

    // 21, 비용 청구
    private long cost;
    // 22, 최종 승인여부 0:발송, 1:승인, 2:반려, 3:진행
    private int status;

    private List<ApproverVO> approvers;
    private List<AttachmentFileVO> attachmentFiles;

    public WorkFlowVO(WorkFlowEntity workFlowEntity) {
        this
                .setId(workFlowEntity.getId())

                .setEmployeeId(workFlowEntity.getEmployeeId())
                .setEmail(workFlowEntity.getEmail())
                .setPhone(workFlowEntity.getPhone())
                .setEmployeeRank(workFlowEntity.getEmployeeRank())
                .setDepartmentId(workFlowEntity.getDepartmentId())
                .setDepartment(workFlowEntity.getDepartment())
                .setEmployeeName(workFlowEntity.getEmployeeName())

                .setCode(workFlowEntity.getCode())
                .setWorkFlowType(WorkFlowType.getMatch(workFlowEntity.getWorkFlowType()))
                .setTitle(workFlowEntity.getTitle())
                .setDescription(workFlowEntity.getDescription())
                .setCost(workFlowEntity.getCost())
                .setDraftDate(workFlowEntity.getDraftDate() != null
                        ? workFlowEntity.getDraftDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                        : null)

                .setStatus(workFlowEntity.getStatus())
                .setApprovers(workFlowEntity.getApprovers() != null ? workFlowEntity.getApprovers().stream().map(ApproverVO::new).toList() : null)
                .setAttachmentFiles(workFlowEntity.getAttachmentFiles() != null ? workFlowEntity.getAttachmentFiles().stream().map(AttachmentFileVO::new).toList() : null)
                ;
    }
}
