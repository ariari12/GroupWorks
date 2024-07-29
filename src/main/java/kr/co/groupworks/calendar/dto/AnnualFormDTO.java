package kr.co.groupworks.calendar.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnualFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용
    private Long employeeId;
    @Future
    @NotNull
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
    private String contents;

}
