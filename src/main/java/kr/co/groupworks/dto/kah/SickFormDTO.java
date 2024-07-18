package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.LeaveType;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SickFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    private String startDate;
    private String endDate;
    private String contents;
    private LeaveType type = LeaveType.SICK;
    private String filePath;
    private String fileName;

}
