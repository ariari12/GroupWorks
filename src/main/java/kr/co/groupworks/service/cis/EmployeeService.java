package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;

import java.util.List;

public interface EmployeeService {
    public void  saveEmployee(EmployeeDTO employee);

    public List<Employee> findAll();


    Employee findByEmployeeId(Long employeeId);
    Employee findByEmployeeEmail(String employeeEmail);

    boolean findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);

}
