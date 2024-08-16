package kr.co.groupworks.calendar.repository;


import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.dto.VacationHistoryDTO;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacationQueryDsl {
    List<CalendarFormDTO> findCalendarFormByEmployee(Employee employee);
    Page<Vacation> findAllTeam(Employee emp, Pageable pageable);

}
