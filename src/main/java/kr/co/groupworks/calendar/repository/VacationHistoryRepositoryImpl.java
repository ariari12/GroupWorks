package kr.co.groupworks.calendar.repository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;


import kr.co.groupworks.calendar.dto.QVacationHistoryDTO;
import kr.co.groupworks.calendar.dto.VacationHistoryDTO;
import kr.co.groupworks.employee.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static kr.co.groupworks.calendar.entity.QVacation.vacation;
import static kr.co.groupworks.calendar.entity.QVacationHistory.*;
import static kr.co.groupworks.department.entity.QDepartment.department;
import static kr.co.groupworks.employee.entity.QEmployee.employee;


@RequiredArgsConstructor
public class VacationHistoryRepositoryImpl implements VacationHistoryQueryDsl {

    private final JPAQueryFactory queryFactory;


    public List<VacationHistoryDTO> findVacationMyHistoryDTO(Long employeeId){

        return queryFactory.select(new QVacationHistoryDTO(employee.employeeId, employee.employeeName,
                        employee.rankName, department.departmentName, vacationHistory.annualDaysUsed,
                        vacationHistory.sickDaysUsed, vacationHistory.otherDaysUsed, vacationHistory.totalAnnual))
                .from(vacationHistory)
                .join(vacationHistory.employee, employee)
                .leftJoin(employee.department, department)
                .where(
                        employee.employeeId.eq(employeeId)
                )
                .fetch();
    }

    @Override
    public Page<VacationHistoryDTO> findAllTeamDTO(Employee emp, Pageable pageable) {
        List<VacationHistoryDTO> contents = queryFactory.select(new QVacationHistoryDTO(employee.employeeId, employee.employeeName,
                        employee.rankName, department.departmentName, vacationHistory.annualDaysUsed,
                        vacationHistory.sickDaysUsed, vacationHistory.otherDaysUsed, vacationHistory.totalAnnual))
                .from(vacationHistory)
                .join(vacationHistory.employee, employee)
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
                .select(vacationHistory.count())
                .from(vacationHistory)
                .join(vacationHistory.employee, employee)
                .leftJoin(employee.department, department)
                .where(department.eq(emp.getDepartment()));
        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }
}
