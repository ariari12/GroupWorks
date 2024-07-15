package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;

public interface WorkFlowService {
    EmployeeDTO getEmployee(String employeeId);
}
