package kr.co.groupworks.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.department.dto.DepartmentDTO;
import kr.co.groupworks.employee.entity.Role;
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
    @Schema(description = "사원 이름", example = "이정민")
    private String employeeName;
    //  이메일
    @Schema(description = "사원 이메일", example = "ljm@gw.com")
    private String email;
    //  부서 번호 fk
    @Schema(description = "부서 정보", implementation = DepartmentDTO.class, example = "{ \"departmentId\": 1, \"departmentName\": \"기술부서\", \"contactNumber\": \"010-1234-5678\", \"location\": \"A동\" }")
    private DepartmentDTO department;
    // 직급 ID
    @Schema(description = "직급 고유 식별자", example = "5")
    private Integer rankId;
    // 직급명
    @Schema(description = "직급명", example = "부장")
    private String rankName;
    // 핸드폰 번호
    @Schema(description = "핸드폰번호", example = "010-1234-5600")
    private String phoneNumber;
    // 직책
    @Schema(description = "직책명", example = "MANAGER")
    private Role role;

}
