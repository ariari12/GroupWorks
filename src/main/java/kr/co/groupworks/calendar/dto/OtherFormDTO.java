package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "사원 ID", example = "1")
    private Long employeeId;

    @NotNull
    @FutureOrPresent
    @Schema(description = "휴가 시작 날짜", example = "2099-08-15")
    private LocalDate otherStartDate;

    @NotNull
    @FutureOrPresent
    @Schema(description = "휴가 종료 날짜", example = "2099-08-16")
    private LocalDate otherEndDate;

    @NotEmpty
    @Schema(description = "휴가 세부 내용", example = "개인적인 사유로 휴가를 신청합니다.")
    private String otherContents;
}
