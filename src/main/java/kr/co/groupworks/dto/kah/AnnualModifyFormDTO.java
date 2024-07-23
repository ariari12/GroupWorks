package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.VacationStatus;
import kr.co.groupworks.entity.kah.VacationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnualModifyFormDTO {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType vacationType;
    private String file;
    private VacationStatus status;
    private String contents;
}
