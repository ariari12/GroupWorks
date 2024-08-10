package kr.co.groupworks.calendar.repository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.groupworks.calendar.dto.QVacationMyHistoryDTO;
import kr.co.groupworks.calendar.dto.VacationMyHistoryDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.co.groupworks.calendar.entity.QVacationHistory.*;
import static kr.co.groupworks.department.entity.QDepartment.department;
import static kr.co.groupworks.employee.entity.QEmployee.employee;


@RequiredArgsConstructor
public class VacationHistoryRepositoryImpl implements VacationHistoryQueryDsl {

    private final JPAQueryFactory queryFactory;


    public List<VacationMyHistoryDTO> findVacationMyHistoryDTO(Long employeeId){

        return queryFactory.select(new QVacationMyHistoryDTO(employee.employeeId, employee.employeeName,
                        employee.rankName, department.departmentName, vacationHistory.annualDaysUsed,
                        vacationHistory.sickDaysUsed, vacationHistory.otherDaysUsed, vacationHistory.totalAnnual))
                .from(vacationHistory)
                .join(vacationHistory.employee, employee)
                .join(employee.department, department)
                .where(
                        employee.employeeId.eq(employeeId)
                )
                .fetch();
    }
}
