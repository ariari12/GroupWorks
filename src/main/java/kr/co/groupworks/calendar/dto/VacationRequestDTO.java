package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.calendar.entity.VacationType;
import kr.co.groupworks.employee.entity.Role;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDTO {
    @Schema(description = "캘린더 ID", example = "123")
    private Long calendarId;

    @Schema(description = "사원 ID", example = "1")
    private Long employeeId;

    @Schema(description = "사원 이름", example = "사원1")
    private String name;

    @Schema(description = "사원 직책", example = "MANAGER")
    private Role role;

    @Schema(description = "휴가 시작 날짜", example = "2024-08-15")
    private String startDate;

    @Schema(description = "휴가 종료 날짜", example = "2024-08-20")
    private String endDate;

    @Schema(description = "휴가 내용", example = "가족과 함께 여행을 가기 위해 휴가를 신청합니다.")
    private String contents;

    @Schema(description = "휴가 종류", example = "ANNUAL")
    private VacationType vacationType;

    @Schema(description = "첨부 파일 목록")
    private List<VacationFileDTO> fileList;

    @Schema(description = "휴가 승인 상태", example = "PENDING")
    private VacationStatus status;
}
