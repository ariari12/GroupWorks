package kr.co.groupworks.dto.kah;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import kr.co.groupworks.entity.kah.VacationType;
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
    @FutureOrPresent
    private String sickStartDate;
    @FutureOrPresent
    private String sickEndDate;
    private String sickContents;
    @NotEmpty
    private String sickFilePath;
    @NotEmpty
    private String sickFileName;

}
