package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
    public Employee findByEmployeeId(Integer employeeId);

    public Employee findByEmployeeIdAndEmployeePWAndEmployeeName(String emplyoeeId, String pw, String name);
}
