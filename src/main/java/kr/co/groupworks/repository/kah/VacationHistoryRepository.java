package kr.co.groupworks.repository.kah;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.VacationHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VacationHistoryRepository extends JpaRepository<VacationHistory, Long> {


    // 수정
    Optional<VacationHistory> findByEmployee(Employee employee);

    @Query("SELECT new kr.co.groupworks.dto.kah.VacationMyHistoryDTO" +
            "(e.employeeId, e.employeeName, e.rankName, " +
            "d.departmentName, vh.annualDaysUsed, vh.sickDaysUsed, vh.otherDaysUsed, vh.totalAnnual)" +
            " FROM VacationHistory vh" +
            " JOIN vh.employee e"+
            " JOIN e.department d"+
            " WHERE e.employeeId = :employeeId")
    List<VacationMyHistoryDTO> findVacationMyHistoryDTO(@Param("employeeId") Long employeeId);
}
