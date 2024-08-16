package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.calendar.entity.VacationStatus;
import lombok.Data;

@Data
public class ApprovalRequestDTO {

    @Schema(description = "휴가 일정의 고유 식별자", example = "1")
    private Long calendarId;

    @Schema(description = "휴가 승인 상태", example = "APPROVED")
    private VacationStatus status;
}
