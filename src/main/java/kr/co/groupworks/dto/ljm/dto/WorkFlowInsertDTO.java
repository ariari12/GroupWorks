package kr.co.groupworks.dto.ljm.dto;

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

@Getter @Setter @ToString
@Accessors(chain = true)
@NoArgsConstructor
public class WorkFlowInsertDTO {
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

    // 14, 최종 결재자 직급
    private String finalApprovalRank;
    // 15, 최종 결재자 소속
    private String finalApprovalDepartment;
    // 16, 최종 결재자 명
    private String finalApprovalName;

    @NotNull
    // 17, 결재 인원
    private int approverCount;
    // 18, 비용 청구
    private long cost;

    public WorkFlowEntity dtoToEntity() {
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
                .finalApprovalRank(finalApprovalRank)
                .finalApprovalDepartment(finalApprovalDepartment)
                .finalApprovalName(finalApprovalName)
                /* 결재 상황 */
                .approverCount(approverCount)
                .build();
    }
}
