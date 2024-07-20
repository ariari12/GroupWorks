package kr.co.groupworks.dto.kah;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.entity.kah.AmPm;
import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HalfFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    @Future
    private LocalDate halfStartDate;
    @NotNull
    private AmPm amPm;
    private String halfContents;
    private VacationType type;
}
