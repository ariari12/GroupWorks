package kr.co.groupworks.calendar.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.employee.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VacationHistoryDTO {

    @Schema(description = "사원 ID", example = "1")
    private Long employeeId;

    @Schema(description = "사원 이름", example = "사원1")
    private String employeeName;

    @Schema(description = "사원 직책", example = "MANAGER")
    private Role role;

    @Schema(description = "직급", example = "과장")
    private String rankName;

    @Schema(description = "부서 이름", example = "기술부서")
    private String departmentName;

    @Schema(description = "연차 누적 사용 일수", example = "5.0")
    private double annualDaysUsed;

    @Schema(description = "병가 사용 일수", example = "2")
    private int sickDaysUsed;

    @Schema(description = "기타 휴가 사용 일수", example = "1")
    private int otherDaysUsed;

    @Schema(description = "남아있는 연차 일수", example = "10.0")
    private double totalAnnual;

    @QueryProjection
    public VacationHistoryDTO(Long employeeId, String employeeName, Role role, String rankName, String departmentName, double annualDaysUsed, int sickDaysUsed, int otherDaysUsed, double totalAnnual) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.rankName = rankName;
        this.departmentName = departmentName;
        this.annualDaysUsed = annualDaysUsed;
        this.sickDaysUsed = sickDaysUsed;
        this.otherDaysUsed = otherDaysUsed;
        this.totalAnnual = totalAnnual;
    }
}
