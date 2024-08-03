package kr.co.groupworks.calendar.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.entity.Calendar;
import kr.co.groupworks.calendar.repository.CalendarRepository;
import kr.co.groupworks.common.mapper.CalendarMapper;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;
    private final EmployeeRepository employeeRepository;
    private final CalendarMapper calendarMapper;
    @Override
    public Long saveCalendar(CalendarFormDTO calendarFormDTO, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));

        Calendar calendar = calendarMapper.toEntity(calendarFormDTO, employee);
        return calendarRepository.save(calendar).getCalendarId();

    }

    @Override
    public List<CalendarFormDTO> findAllPersonalCalendar(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));

        return calendarRepository.findAllPersonalCalendarByEmployee(employee).
                stream().map(calendarMapper::toDto).toList();
    }

    @Override
    public Long deleteCalendar(Long calendarId, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사원을 찾을 수 없습니다. " + employeeId));
        Calendar calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다. " + calendarId));
        calendarRepository.deleteByCalendarIdAndEmployee(calendar.getCalendarId(), employee);
        return calendar.getCalendarId();
    }

    @Override
    public Long modifyCalendar(CalendarFormDTO calendarFormDTO, Long employeeId) {
        Calendar calendar = calendarRepository.findById(calendarFormDTO.getCalendarId())
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다. "
                        + calendarFormDTO.getCalendarId()));
        calendar.updateCalendar(calendarFormDTO);

        return calendar.getCalendarId();
    }
}
