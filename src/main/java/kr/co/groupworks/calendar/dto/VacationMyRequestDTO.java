package kr.co.groupworks.calendar.dto;

import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.calendar.entity.VacationType;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationMyRequestDTO {
    //이름 잔여연차 잔여병가	기타 사용일
    //기간 종류 사용기간	증명자료 승인
    private Long calendarId;
    // 이름
    private String name;
    // 신청 휴가 기간
    private String startDate;
    private String endDate;
    private String contents;
    // 휴가 종류
    private VacationType vacationType;
    // 첨부파일들
    private List<VacationFileDTO> fileList;
    // 승인 상태
    private VacationStatus status;
}
