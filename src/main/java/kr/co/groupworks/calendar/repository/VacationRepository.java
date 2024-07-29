package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long>{
    @Query("SELECT v FROM Vacation v JOIN v.employee e WHERE e.employeeId = :employeeId")
    List<Vacation> findAllByEmployeeId(@Param("employeeId") Long employeeId);

    // 사원의 휴가기간이 겹치치 않는 휴가 조회
    @Query("select v from Vacation v JOIN v.employee e where e.employeeId = :employeeId and " +
            "(v.startDate <= :endDate and v.endDate >= :startDate)")
    List<Vacation> findOverlappingVacations(@Param("employeeId") Long employeeId,
                                            @Param("startDate") String startDate,
                                            @Param("endDate") String endDate);

    @Modifying
    void deleteByCalendarId(@Param("calendarId") Long calendarId);
}
