package kr.co.groupworks.calendar.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VacationMyHistoryDTO {

    private Long employeeId;
    private String employeeName;
    private String rankName;
    private String departmentName;
    private double annualDaysUsed;
    private int sickDaysUsed;
    private int otherDaysUsed;
    private double totalAnnual;

    @QueryProjection
    public VacationMyHistoryDTO(Long employeeId, String employeeName, String rankName, String departmentName, double annualDaysUsed, int sickDaysUsed, int otherDaysUsed, double totalAnnual) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.rankName = rankName;
        this.departmentName = departmentName;
        this.annualDaysUsed = annualDaysUsed;
        this.sickDaysUsed = sickDaysUsed;
        this.otherDaysUsed = otherDaysUsed;
        this.totalAnnual = totalAnnual;
    }
}
