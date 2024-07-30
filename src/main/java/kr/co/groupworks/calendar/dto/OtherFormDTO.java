package kr.co.groupworks.calendar.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtherFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;

    @NotNull
    @FutureOrPresent
    private LocalDate otherStartDate;
    @NotNull
    @FutureOrPresent
    private LocalDate otherEndDate;
    @NotEmpty
    private String otherContents;
    private String otherFilePath;
    private String otherFileName;

}