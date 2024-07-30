package kr.co.groupworks.calendar.dto;

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
    private Long calendarId;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private VacationType vacationType;
    private VacationStatus status;
    private String contents;
    // 수정 날짜
    private String date;
    // 첨부파일들
    private List<VacationFileDTO> fileList;
}
