package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.entity.CalendarAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarAttachmentRepository extends JpaRepository<CalendarAttachment, Long> {
}
