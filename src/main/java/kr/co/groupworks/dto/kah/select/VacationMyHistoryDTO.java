package kr.co.groupworks.dto.kah.select;

import kr.co.groupworks.entity.kah.VacationStatus;
import kr.co.groupworks.entity.kah.VacationType;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VacationMyHistoryDTO {
    //이름 잔여연차 잔여병가	기타 사용일
    //기간 종류 사용기간	증명자료 승인
    // 이름
    private String name;
    // 신청 휴가 기간
    private String startDate;
    private String endDate;
    // 휴가 종류
    private VacationType vacationType;
    // 첨부파일이름
    private String fileName;
    // 승인 상태
    private VacationStatus status;
}
