package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.kah.Vacation;

import java.util.List;

public interface VacationService {
    Vacation save(AnnualFormDTO dto, Long employeeId);

    List<VacationMyHistoryDTO> findAllByEmployeeId(Long employeeId);


}
