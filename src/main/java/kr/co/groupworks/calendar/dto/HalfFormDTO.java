package kr.co.groupworks.calendar.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.calendar.entity.AmPm;
import kr.co.groupworks.calendar.entity.VacationType;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HalfFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    @Future
    @NotNull
    private LocalDate halfStartDate;
    @NotNull
    private AmPm amPm;
    private String halfContents;
    private VacationType type;
}
