package kr.co.groupworks.calendar.repository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.employee.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import java.util.List;

import static kr.co.groupworks.calendar.entity.QCalendarAttachment.calendarAttachment;
import static kr.co.groupworks.calendar.entity.QVacation.*;
import static kr.co.groupworks.department.entity.QDepartment.*;
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

    @Override
    public Page<Vacation> findAllTeam(Employee emp, Pageable pageable) {

        List<Vacation> contents = queryFactory
                .select(vacation).distinct()
                .from(vacation)
                .join(vacation.employee, employee)
                .leftJoin(vacation.attachmentList, calendarAttachment)
                .leftJoin(employee.department, department)
                .where(employee.rankId
                        .loe(emp.getRankId())
                        .and(
                            department.eq(emp.getDepartment())
                        )
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(vacation.count())
                .from(vacation)
                .join(vacation.employee, employee)
                .leftJoin(employee.department, department)
                .where(department.eq(emp.getDepartment()));



        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }

//    SELECT DISTINCT v.*
//    FROM vacation v
//    JOIN calendar c ON v.calendar_id = c.calendar_id
//    JOIN employee e ON c.employee_id = e.employee_id
//    LEFT JOIN calendar_attachment ca ON v.calendar_id = ca.calendar_id
//    LEFT JOIN department d ON e.department_id = d.department_id
//    WHERE d.department_id = 1
}
