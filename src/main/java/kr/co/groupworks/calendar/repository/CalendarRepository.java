package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.Calendar;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Modifying
    void deleteByCalendarIdAndEmployee(Long calendarId, Employee employee);
}
