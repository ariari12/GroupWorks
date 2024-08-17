package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.dto.VacationHistoryDTO;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface VacationHistoryQueryDsl {
    List<VacationHistoryDTO> findVacationMyHistoryDTO(Long employeeId);

    Page<VacationHistoryDTO> findAllTeamDTO(Employee employee, Pageable pageable);
}
