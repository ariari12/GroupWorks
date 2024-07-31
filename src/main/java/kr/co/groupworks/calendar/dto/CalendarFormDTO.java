package kr.co.groupworks.calendar.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarFormDTO {
    // 내용
    private String contents;
    // 일정 시작일
    private String startDate;
    // 일정 종료일
    private String endDate;
}
