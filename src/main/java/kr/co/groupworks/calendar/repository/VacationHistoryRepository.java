package kr.co.groupworks.calendar.repository;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.calendar.entity.VacationHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacationHistoryRepository extends JpaRepository<VacationHistory, Long>,VacationHistoryQueryDsl {


    // 수정
    Optional<VacationHistory> findByEmployee(Employee employee);

//    @Query("SELECT new kr.co.groupworks.calendar.dto.VacationMyHistoryDTO" +
//            "(e.employeeId, e.employeeName, e.rankName, " +
//            "d.departmentName, vh.annualDaysUsed, vh.sickDaysUsed, vh.otherDaysUsed, vh.totalAnnual)" +
//            " FROM VacationHistory vh" +
//            " JOIN vh.employee e"+
//            " JOIN e.department d"+
//            " WHERE e.employeeId = :employeeId")
//    List<VacationMyHistoryDTO> findVacationMyHistoryDTO(@Param("employeeId") Long employeeId);
}
