package kr.co.groupworks.repository.kah;

import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;


import java.util.List;

public interface VacationHistoryQueryDsl {
    List<VacationMyHistoryDTO> findVacationMyHistoryDTO(Long employeeId);
}
