package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnualFormDTO {
    @Schema(description = "사원 고유 식별자", example = "1")
    private Long employeeId;

    @Schema(description = "휴가 시작 날짜", example = "2099-12-01")
    @Future
    @NotNull
    private LocalDate startDate;

    @Schema(description = "휴가 종료 날짜", example = "2099-12-10")
    @Future
    private LocalDate endDate;

    @Schema(description = "휴가 관련 상세 내용", example = "연말 휴가")
    private String contents;

}
