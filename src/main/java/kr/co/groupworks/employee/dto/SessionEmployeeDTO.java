package kr.co.groupworks.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.department.dto.DepartmentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SessionEmployeeDTO {
    //  사원번호 pk
    @Schema(description = "사원 고유 식별자", example = "1")
    private Long employeeId;
    //  사원명
    @Schema(description = "사원 이름", example = "사원1")
    private String employeeName;
    //  이메일
    @Schema(description = "사원 이메일", example = "employee1@example.com")
    private String email;
    //  부서 번호 fk
    @Schema(description = "부서 정보", implementation = DepartmentDTO.class, example = "{ \"departmentId\": 1, \"departmentName\": \"기술부서\", \"contactNumber\": \"010-1234-5678\", \"location\": \"A동\" }")
    private DepartmentDTO department;
    // 직급 ID
    @Schema(description = "직급 고유 식별자", example = "2")
    private Integer rankId;
    // 직급명
    @Schema(description = "직급명", example = "대리")
    private String rankName;
    // 핸드폰 번호
    @Schema(description = "핸드폰번호", example = "010-1111-2222")
    private String phoneNumber;


}
