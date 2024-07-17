package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.entity.kah.Vacation;

public interface VacationService {
    Vacation save(AnnualFormDTO dto, Long employeeId);


}
