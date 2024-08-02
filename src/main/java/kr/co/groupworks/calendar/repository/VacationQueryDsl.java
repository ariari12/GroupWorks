package kr.co.groupworks.calendar.repository;


import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.employee.entity.Employee;

import java.util.List;

public interface VacationQueryDsl {
    List<CalendarFormDTO> findCalendarFormByEmployee(Employee employee);

}
