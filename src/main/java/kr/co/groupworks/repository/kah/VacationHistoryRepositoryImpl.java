package kr.co.groupworks.repository.kah;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.groupworks.dto.kah.QVacationMyHistoryDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kr.co.groupworks.entity.cis.QDepartment.department;
import static kr.co.groupworks.entity.cis.QEmployee.employee;
import static kr.co.groupworks.entity.kah.QVacationHistory.vacationHistory;

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
                .where(employee.employeeId.eq(employeeId))
                .fetch();
    }
}
