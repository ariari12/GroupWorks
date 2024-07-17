package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.LeaveType;
import lombok.*;


@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnnualFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용
    private String startDate;
    private String endDate;
    private String contents;
    private LeaveType type = LeaveType.ANNUAL;

}
