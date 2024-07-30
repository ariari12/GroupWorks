package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.Calendar;
import kr.co.groupworks.calendar.entity.CalendarAttachment;
import kr.co.groupworks.calendar.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface CalendarAttachmentRepository extends JpaRepository<CalendarAttachment, Long> {
    @Modifying
    void deleteByCalendar(Calendar calendar);
}
