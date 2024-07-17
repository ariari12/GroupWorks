package kr.co.groupworks.dto.ljm.employee;

import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.dto.WorkFlowDTO;
import kr.co.groupworks.entity.cis.Employee;
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
    private long departmentId;
    private String departmentName;

    public static EmployeeDTO entityToDto(Employee employee) {
        return new EmployeeDTO()
                .setId(employee.getEmployeeId())
                .setName(employee.getEmployeeName())
                .setEmail(employee.getEmail())
                .setPhone(employee.getPhoneNumber())
                .setRank(employee.getRankName())
                .setDepartmentId(employee.getDepartmentId())
                .setDepartmentName(employee.getDepartmentName());
    }

    public static WorkFlowDTO entityToWorkflowDTO(Employee employee) {
        return new WorkFlowDTO()
                .setEmployeeId(employee.getEmployeeId())
                .setEmployeeName(employee.getEmployeeName())
                .setEmail(employee.getEmail())
                .setPhone(employee.getPhoneNumber())
                .setEmployeeRank(employee.getRankName())
                .setDepartmentId(employee.getDepartmentId())
                .setDepartment(employee.getDepartmentName())
                ;
    }

    public static ApproverDTO entityToApproverDto(Employee employee) {
        return new ApproverDTO()
                .setEmployeeId(employee.getEmployeeId())
                .setApproverName(employee.getEmployeeName())
                .setApproverEmail(employee.getEmail())
                .setApproverPhone(employee.getPhoneNumber())
                .setApproverRank(employee.getRankName())
                .setDepartment(employee.getDepartmentName())
                ;
    }
}
