package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarFormDTO implements Serializable {
    @Schema(description = "캘린더 고유 식별자", example = "100")
    private Long calendarId;

    @Schema(description = "캘린더 제목", example = "팀 회의")
    private String title;

    @Schema(description = "캘린더 상세 내용", example = "2024년 12월 팀 전략 회의")
    private String contents;

    @Schema(description = "일정 시작 날짜", example = "2099-12-05")
    private String startDate;

    @Schema(description = "일정 종료 날짜", example = "2099-12-05")
    private String endDate;
}
