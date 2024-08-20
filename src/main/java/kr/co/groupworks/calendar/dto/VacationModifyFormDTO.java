package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.calendar.entity.VacationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationModifyFormDTO {
    @Schema(description = "캘린더 ID", example = "123")
    private Long calendarId;

    @Schema(description = "사원 ID", example = "1")
    private Long employeeId;

    @Schema(description = "휴가 시작 날짜", example = "2099-08-15")
    private LocalDate startDate;

    @Schema(description = "휴가 종료 날짜", example = "2099-08-20")
    private LocalDate endDate;

    @Schema(description = "휴가 유형", example = "ANNUAL")
    private VacationType vacationType;

    @Schema(description = "현재 휴가 상태", example = "PENDING")
    private VacationStatus status;

    @Schema(description = "휴가 세부 내용", example = "여름 휴가를 위해 연차를 사용합니다.")
    private String contents;

    @Schema(description = "휴가 시작과 종료 합친 날짜")
    private String date;

    @Schema(description = "첨부된 파일 리스트")
    private List<VacationFileDTO> fileList;
}
