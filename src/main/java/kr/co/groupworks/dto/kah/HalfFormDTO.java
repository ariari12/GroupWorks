package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HalfFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    private String startDate;
    private String amPm;
    private String contents;
    private int remainingVacationDays;
    private VacationType type = VacationType.HALF;

    private String filePath;
    private String fileName;
}
