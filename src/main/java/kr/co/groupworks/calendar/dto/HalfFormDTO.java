package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import kr.co.groupworks.calendar.entity.AmPm;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HalfFormDTO {
    @Schema(description = "사원의 ID", example = "1")
    private Long employeeId;

    @Schema(description = "반차 시작 날짜", example = "2098-12-01", type = "string", format = "date")
    @Future
    @NotNull
    private LocalDate halfStartDate;

    @Schema(description = "오전/오후 선택", example = "AM")
    @NotNull
    private AmPm amPm;

    @Schema(description = "반차 내용", example = "병원 방문")
    private String halfContents;
}
