package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    public Employee findByEmployeeId(String employeeId);

    public Employee findByEmployeeIdAndEmployeePWAndEmployeeName(String emplyoeeId, String pw, String name);
}
