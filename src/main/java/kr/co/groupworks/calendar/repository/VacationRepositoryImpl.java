package kr.co.groupworks.calendar.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
public class VacationRepositoryImpl implements VacationQueryDsl{
    private final JPAQueryFactory queryFactory;
//    List<VacationMyHistoryDTO> contents = queryFactory.select(new QVacationMyHistoryDTO(employee.employeeId, employee.employeeName,
//                    employee.rankName, department.departmentName, vacationHistory.annualDaysUsed,
//                    vacationHistory.sickDaysUsed, vacationHistory.otherDaysUsed, vacationHistory.totalAnnual))
//            .from(vacationHistory)
//            .join(vacationHistory.employee, employee)
//            .join(employee.department, department)
//            .where(employee.employeeId.eq(employeeId))
//            .offset(pageable.getOffset())
//            .limit(pageable.getPageSize())
//            .fetch();
//
//    PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne)

}
