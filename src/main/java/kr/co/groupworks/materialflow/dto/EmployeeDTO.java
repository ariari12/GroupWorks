package kr.co.groupworks.materialflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class EmployeeDTO {
    @Schema(description = "사원 등록 번호", defaultValue = "1")
    private Long id;
    @Schema(description = "사원 성명", defaultValue = "홍길동")
    private String name;
    @Schema(description = "소속 부서 명", defaultValue = "영업팀")
    private String department;
    @Schema(description = "연락처", defaultValue = "010-1234-5678")
    private String phone;
    @Schema(description = "이메일", defaultValue = "홍길동전@test.com")
    private String email;

    public EmployeeDTO(Employee e) {
        this
                .setId(e.getEmployeeId())
                .setName(e.getEmployeeName())
                .setDepartment(e.getDepartment().getDepartmentName())
                .setPhone(e.getPhoneNumber())
                .setEmail(e.getEmail())
        ;
    }
}
