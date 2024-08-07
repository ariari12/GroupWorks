package kr.co.groupworks.calendar.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.entity.QVacation;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.entity.QEmployee;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.co.groupworks.calendar.entity.QVacation.*;
import static kr.co.groupworks.employee.entity.QEmployee.*;


@RequiredArgsConstructor
public class VacationRepositoryImpl implements VacationQueryDsl{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CalendarFormDTO> findCalendarFormByEmployee(Employee emp) {
        return queryFactory.select(
                Projections.constructor(
                        CalendarFormDTO.class,
                        vacation.calendarId,
                        vacation.title,
                        vacation.contents,
                        vacation.startDate,
                        vacation.endDate
                ))
                .from(vacation)
                .join(vacation.employee, employee)
                .where(employee.eq(emp))
                .fetch();
    }
}
