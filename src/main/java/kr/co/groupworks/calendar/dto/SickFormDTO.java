package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SickFormDTO {
    @Schema(description = "사원 ID", example = "1")
    private Long employeeId;

    @NotNull
    @Schema(description = "병가 시작 날짜", example = "2099-08-20")
    private LocalDate sickStartDate;

    @NotNull
    @Schema(description = "병가 종료 날짜", example = "2099-08-22")
    private LocalDate sickEndDate;

    @Schema(description = "병가 세부 내용", example = "질병으로 인해 병가를 신청합니다.")
    private String sickContents;
}
