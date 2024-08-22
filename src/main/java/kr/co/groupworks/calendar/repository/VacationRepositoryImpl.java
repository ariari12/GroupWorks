package kr.co.groupworks.calendar.repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.groupworks.calendar.dto.CalendarFormDTO;
import kr.co.groupworks.calendar.entity.Vacation;
import kr.co.groupworks.calendar.entity.VacationStatus;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.entity.Role;
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
                .where(employee.eq(emp).and(vacation.status.eq(VacationStatus.APPROVED)))
                .fetch();
    }

    @Override
    public Page<Vacation> findAllTeamSearchName(Employee emp, Pageable pageable, String searchName) {

        BooleanBuilder whereClause = new BooleanBuilder();
//        whereClause.and(employee.rankId.loe(emp.getRankId()).and(department.eq(emp.getDepartment())));
        NumberExpression<Integer> roleGradeExpression = new CaseBuilder()
                .when(employee.role.eq(Role.ASSOCIATE)).then(1)
                .when(employee.role.eq(Role.JUNIOR)).then(2)
                .when(employee.role.eq(Role.SENIOR)).then(3)
                .when(employee.role.eq(Role.MANAGER)).then(4)
                .otherwise(0);

        whereClause.and(roleGradeExpression.loe(emp.getRole().getGrade()))
                .and(employee.department.eq(emp.getDepartment()));

        if (searchName != null && !searchName.isEmpty()) {
            whereClause.and(employee.employeeName.like("%"+searchName+"%"));
        }

        List<Vacation> contents = queryFactory
                .select(vacation).distinct()
                .from(vacation)
                .join(vacation.employee, employee)
                .leftJoin(vacation.attachmentList, calendarAttachment)
                .leftJoin(employee.department, department)
                .where(whereClause)
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

    @Override
    public List<Vacation> findAllByEmployeeId(Long employeeId) {
        return queryFactory.selectFrom(vacation)
                .join(vacation.employee, employee)
                .leftJoin(vacation.attachmentList, calendarAttachment).fetchJoin()
                .where(employee.employeeId.eq(employeeId))
                .fetch();
    }
}
