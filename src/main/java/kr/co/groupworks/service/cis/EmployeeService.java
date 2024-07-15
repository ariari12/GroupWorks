package kr.co.groupworks.service.cis;

import kr.co.groupworks.entity.cis.Employee;

import java.util.List;

public interface EmployeeService {
    public void  saveEmployee(Employee employee);

    public List<Employee> findAll();


    Employee findByEmployeeId(Long employeeId);

    boolean findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);

}
