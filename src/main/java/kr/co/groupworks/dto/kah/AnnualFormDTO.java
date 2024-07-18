package kr.co.groupworks.dto.kah;

import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnnualFormDTO {
    //남은 휴가, 휴일 일정, 상세 내용
    private String startDate;
    private String endDate;
    private String contents;
    private VacationType type = VacationType.ANNUAL;

}
