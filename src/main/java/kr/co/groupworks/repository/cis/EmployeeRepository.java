package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByEmployeeId(Long employeeId);

    public Employee findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);
}
