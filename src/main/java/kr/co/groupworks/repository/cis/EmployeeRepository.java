package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);

    public Employee findByEmail(String email);

    @Query(value = "select distinct e.departmentId, e.departmentName from Employee e")
    Map<Long, String> getDepartments();

}
