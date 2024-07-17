package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.LeaveType;
import lombok.*;


@Getter
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
    private LeaveType type = LeaveType.HALF;

    private String filePath;
    private String fileName;
}
