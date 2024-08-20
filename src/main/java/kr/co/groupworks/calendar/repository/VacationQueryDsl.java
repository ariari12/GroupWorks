package kr.co.groupworks.calendar.repository;


import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.dto.VacationHistoryDTO;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationQueryDsl {
    List<CalendarFormDTO> findCalendarFormByEmployee(Employee employee);
    Page<Vacation> findAllTeamSearchName(Employee emp, Pageable pageable, String searchName);
    //fetch 조인으로 바꿔야함
//    @Query("SELECT v FROM Vacation v JOIN v.employee e " +
//            "left join CalendarAttachment ca ON ca.calendar.calendarId = v.calendarId WHERE e.employeeId = :employeeId")
//    List<Vacation> findAllByEmployeeId(@Param("employeeId") Long employeeId);

    List<Vacation> findAllByEmployeeId(Long employeeId);

}
