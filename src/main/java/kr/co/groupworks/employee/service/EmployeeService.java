package kr.co.groupworks.employee.service;

import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public void  saveEmployee(EmployeeDTO employee);

    public List<Employee> findAll();


    EmployeeDTO findByEmployeeId(Long employeeId);
    Employee findByEmployeeEmail(String employeeEmail);

    boolean findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);

    boolean isEqualPassword(String checkPW, String currentPW);

    List<String> getEmailsByQuery(String query);

    List<EmployeeDTO> findByDepartmentId(Long departmentId);

    List<Integer> findRankIdByDepartmentId(Long departmentId);

    EmployeeDTO findSupervisorEmployeeByEmployeeId(Long employeeId);

    void updatePhoneNumberByEmployee(EmployeeDTO employeeDTO);
}
