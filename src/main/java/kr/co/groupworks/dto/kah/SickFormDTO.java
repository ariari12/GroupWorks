package kr.co.groupworks.dto.kah;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SickFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    @NotNull
    @FutureOrPresent
    private LocalDate sickStartDate;
    @NotNull
    @FutureOrPresent
    private LocalDate sickEndDate;
    private String sickContents;

}
