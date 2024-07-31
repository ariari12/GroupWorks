package kr.co.groupworks.workflow.dto.employee;

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
    private long id;
    private String name;
    private String email;
    private String phone;
    private String rank;
    private Long departmentId;
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
