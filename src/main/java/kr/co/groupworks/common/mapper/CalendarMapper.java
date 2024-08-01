package kr.co.groupworks.common.mapper;

import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.entity.Calendar;
import kr.co.groupworks.entity.cis.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    @Mapping(target = "employee", source = "employee")
    Calendar toEntity(CalendarFormDTO calendarFormDTO, Employee employee);
}
