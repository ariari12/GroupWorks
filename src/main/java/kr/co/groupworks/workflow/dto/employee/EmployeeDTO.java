package kr.co.groupworks.workflow.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class EmployeeDTO {
    @Schema(description = "사원 고유 식별자", example = "1")
    private long id;

    @Schema(description = "사원 이름", example = "사원1")
    private String name;

    @Schema(description = "사원 이메일", example = "employee1@example.com")
    private String email;

    @Schema(description = "사원 전화번호", example = "010-1111-1100")
    private String phone;

    @Schema(description = "사원 직급", example = "부장")
    private String rank;

    @Schema(description = "부서 ID", example = "1")
    private Long departmentId;

    @Schema(description = "부서 이름", example = "기술부서")
    private String departmentName;

    public static EmployeeDTO entityToDto(Employee employee) {
        return new EmployeeDTO()
                .setId(employee.getEmployeeId())
                .setName(employee.getEmployeeName())
                .setEmail(employee.getEmail())
                .setPhone(employee.getPhoneNumber())
                .setRank(employee.getRankName())
                .setDepartmentId(employee.getDepartment() == null ? 0 : employee.getDepartment().getDepartmentId())
                .setDepartmentName(employee.getDepartment() == null ? null : employee.getDepartment().getDepartmentName())
        ;
    }

    public static WorkFlowDTO entityToWorkflowDTO(Employee employee) {
        return new WorkFlowDTO()
                .setEmployeeId(employee.getEmployeeId())
                .setEmployeeName(employee.getEmployeeName())
                .setEmail(employee.getEmail())
                .setPhone(employee.getPhoneNumber())
                .setEmployeeRank(employee.getRankName())
                .setDepartmentId(employee.getDepartment() == null ? 0 : employee.getDepartment().getDepartmentId())
                .setDepartment(employee.getDepartment() == null ? null : employee.getDepartment().getDepartmentName())
                ;
    }

    public static ApproverDTO entityToApproverDto(Employee employee) {
        return new ApproverDTO()
                .setEmployeeId(employee.getEmployeeId())
                .setApproverName(employee.getEmployeeName())
                .setApproverEmail(employee.getEmail())
                .setApproverPhone(employee.getPhoneNumber())
                .setApproverRank(employee.getRankName())
                .setDepartment(employee.getDepartment() == null ? null : employee.getDepartment().getDepartmentName())
                ;
    }
}
