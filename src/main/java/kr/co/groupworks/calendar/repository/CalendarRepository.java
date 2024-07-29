package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
