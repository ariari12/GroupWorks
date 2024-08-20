package kr.co.groupworks.department.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    // 1, 부서 pk
    @Schema(description = "부서 고유 식별자", example = "1")
    private Long departmentId;
    // 2, 부서 명
    @Schema(description = "부서 이름", example = "기술부서")
    private String departmentName;
    // 3, 부서 공용 연락처
    @Schema(description = "부서 공용 연락처", example = "010-1234-5678")
    private String contactNumber;
    // 4, 부서 지역
    @Schema(description = "부서 위치", example = "A동")
    private String location;
}
