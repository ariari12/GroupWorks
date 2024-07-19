package kr.co.groupworks.dto.kah;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnnualFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용
    private Long employeeId;
    @Future
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    private String contents;
    private VacationType type = VacationType.ANNUAL;

}
