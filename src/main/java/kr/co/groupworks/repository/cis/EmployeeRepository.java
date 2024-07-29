package kr.co.groupworks.repository.cis;

import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);

    public Employee findByEmail(String email);


}
