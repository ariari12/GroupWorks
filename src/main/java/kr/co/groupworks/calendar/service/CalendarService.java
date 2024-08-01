package kr.co.groupworks.calendar.service;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;

public interface CalendarService {

    Long saveCalendar(CalendarFormDTO calendarFormDTO, Long employeeId);
}
