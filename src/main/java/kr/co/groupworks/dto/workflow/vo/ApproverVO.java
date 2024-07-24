package kr.co.groupworks.dto.workflow.vo;

import kr.co.groupworks.dto.workflow.ApprovalMethod;
import kr.co.groupworks.dto.workflow.dto.ApproverDTO;
import kr.co.groupworks.entity.workflow.ApproverEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@ToString
@Accessors(chain = true)
public class ApproverVO {
    // 1, 결재자 pk
    private long id;
    // 2, 결재요청 fk
    private long workFlowId;
    // 3, 결재 순서
    private int sequenceNum;

    // 4, 1:결재자, 2:협조자, 3:참조자 구분
    private int approverType;
    // 5, 결재자 사원 fk
    private long employeeId;
    // 6, 결재자 이메일
    private String approverEmail;
    // 7, 결재자 연락처
    private String approverPhone;
    // 8, 결재자 명
    private String approverName;
    // 9, 결재자 직급
    private String approverRank;
    // 10, 결재자 소속
    private String department;

    // 11, 결재 방식 String
    private String approvalMethod;
    // 12, 반려사유/협의안/코멘트
    private String comment;
    // 13, 결재 승인 일자
    private String approvalDate;
    // 14, 승인 여부 String
    private int approval;

    public ApproverVO(ApproverEntity approverEntity) {
        this
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

                .setApprovalMethod(ApprovalMethod.getMatch(approverEntity.getApprovalMethod()))
                .setComment(approverEntity.getComment())
                .setApprovalDate(approverEntity.getApprovalDate() != null
                        ? approverEntity.getApprovalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                        : null)
                .setApproval(approverEntity.getApproval())
        ;
    }

    public ApproverVO(ApproverDTO approverDTO) {
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$";
        this.approvalDate = approverDTO.getApprovalDate();
        if(approvalDate != null && approvalDate.matches(datePattern)) {
            approvalDate = LocalDateTime.parse(approvalDate, DateTimeFormatter.ofPattern(ApproverDTO.STR_TO_LDT_PATTERN))
                    .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }

        this
                .setId(approverDTO.getId())
                .setWorkFlowId(approverDTO.getWorkFlowId())
                .setSequenceNum(approverDTO.getSequenceNum())

                .setApproverType(approverDTO.getApproverType())
                .setEmployeeId(approverDTO.getEmployeeId())
                .setApproverEmail(approverDTO.getApproverEmail())
                .setApproverPhone(approverDTO.getApproverPhone())
                .setApproverName(approverDTO.getApproverName())
                .setApproverRank(approverDTO.getApproverRank())
                .setDepartment(approverDTO.getDepartment())

                .setApprovalMethod(ApprovalMethod.getMatch(approverDTO.getApprovalMethod()))
                .setComment(approverDTO.getComment())
                .setApproval(approverDTO.getApproval())
                ;
    }
}