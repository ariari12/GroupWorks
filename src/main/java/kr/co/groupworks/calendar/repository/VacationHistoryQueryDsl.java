package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.dto.VacationHistoryDTO;
import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface VacationHistoryQueryDsl {
    Optional<VacationHistoryDTO> findVacationMyHistoryDTO(Long employeeId);

    Page<VacationHistoryDTO> findAllTeamDTO(Employee employee, Pageable pageable);
}
