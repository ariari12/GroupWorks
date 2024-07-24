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

    @Query("SELECT new kr.co.groupworks.dto.kah.VacationMyHistoryDTO" +
            "(e.employeeId, e.employeeName, d.departmentName, e.annualDaysUsed, e.sickDaysUsed, e.otherDaysUsed)" +
            " FROM Employee e" +
            " JOIN e.department d"+
            " WHERE e.employeeId = :employeeId AND e.employeeId = d.departmentId")
    List<VacationMyHistoryDTO> findVacationMyHistoryDTO(@Param("employeeId") Long employeeId);


}
