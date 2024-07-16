package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;

public interface VacationService {
    void save(AnnualFormDTO dto, Long employeeId);
}
