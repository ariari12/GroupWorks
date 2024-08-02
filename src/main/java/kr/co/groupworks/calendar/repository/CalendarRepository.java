package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.Calendar;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Modifying
    void deleteByCalendarIdAndEmployee(Long calendarId, Employee employee);

    @Query("SELECT c FROM Calendar c WHERE TYPE(c) = Calendar AND c.employee = :employee")
    List<Calendar> findAllPersonalCalendarByEmployee(@Param(value = "employee") Employee employee);
}
