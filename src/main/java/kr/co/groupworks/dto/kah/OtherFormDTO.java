package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtherFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    private String otherStartDate;
    private String otherEndDate;
    private String otherContents;
    private String otherFilePath;
    private String otherFileName;

}
