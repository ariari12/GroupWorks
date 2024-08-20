package kr.co.groupworks.calendar.service;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;

import java.util.List;

public interface CalendarService {

    Long saveCalendar(CalendarFormDTO calendarFormDTO, Long employeeId);

    List<CalendarFormDTO> findAllPersonalCalendar(Long employeeId);

    Long deleteCalendar(Long calendarId, Long employeeId);

    Long modifyCalendar(CalendarFormDTO calendarFormDTO, Long employeeId);
}
