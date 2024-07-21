package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OtherFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용, 사원 번호,
    private Long employeeId;
    private String startDate;
    private String endDate;
    private String contents;
    private VacationType type = VacationType.OTHER;
    private String filePath;
    private String fileName;

}
