package kr.co.groupworks.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationFileDTO {
    private String fileName;
    // 실제 저장 파일 경로
    private String filePath;
}
