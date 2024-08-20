package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.VacationHistory;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacationHistoryRepository extends JpaRepository<VacationHistory, Long>,VacationHistoryQueryDsl {


    // 수정
    Optional<VacationHistory> findByEmployee(Employee employee);

}
