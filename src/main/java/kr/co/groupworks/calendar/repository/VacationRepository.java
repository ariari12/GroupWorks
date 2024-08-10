package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VacationRepository extends JpaRepository<Vacation, Long>, VacationQueryDsl {
    //fetch 조인으로 바꿔야함
    @Query("SELECT v FROM Vacation v JOIN v.employee e " +
            "left join CalendarAttachment ca ON ca.calendar.calendarId = v.calendarId WHERE e.employeeId = :employeeId")
    List<Vacation> findAllByEmployeeId(@Param("employeeId") Long employeeId);

    // 사원의 휴가기간이 겹치치 않는 휴가 조회
    @Query("select v from Vacation v JOIN v.employee e where e.employeeId = :employeeId and " +
            "(v.startDate <= :endDate and v.endDate >= :startDate)")
    List<Vacation> findOverlappingVacations(@Param("employeeId") Long employeeId,
                                            @Param("startDate") String startDate,
                                            @Param("endDate") String endDate);
    Optional<Vacation> findByCalendarIdAndEmployee(Long calendarId, Employee employee);

    @Modifying
    void deleteByCalendarId(@Param("calendarId") Long calendarId);
}
