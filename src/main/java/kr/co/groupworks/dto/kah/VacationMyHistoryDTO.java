package kr.co.groupworks.dto.kah;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationMyHistoryDTO {

    private Long employeeId;
    private String employeeName;
    private String rankName;
    private String departmentName;
    private double annualDaysUsed;
    private int sickDaysUsed;
    private int otherDaysUsed;
    private int totalAnnual;
}
