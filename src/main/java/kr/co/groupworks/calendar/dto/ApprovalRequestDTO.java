package kr.co.groupworks.calendar.dto;

import kr.co.groupworks.calendar.entity.VacationStatus;
import lombok.Data;

@Data
public class ApprovalRequestDTO {
    private Long calendarId;
    private VacationStatus status;
}
