package kr.co.groupworks.calendar.repository;

import kr.co.groupworks.calendar.dto.VacationMyHistoryDTO;


import java.util.List;

public interface VacationHistoryQueryDsl {
    List<VacationMyHistoryDTO> findVacationMyHistoryDTO(Long employeeId);
}
