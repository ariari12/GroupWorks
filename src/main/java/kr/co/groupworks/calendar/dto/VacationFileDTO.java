package kr.co.groupworks.calendar.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacationFileDTO {
    @Schema(description = "파일 이름", example = "병가_증명서.pdf")
    private String fileName;

    @Schema(description = "실제 파일 저장 경로", example = "/files/병가_증명서.pdf")
    private String filePath;
}
