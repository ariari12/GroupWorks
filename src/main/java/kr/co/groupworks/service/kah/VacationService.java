package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.HalfFormDTO;
import kr.co.groupworks.dto.kah.SickFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.kah.Vacation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VacationService {
    Long save(AnnualFormDTO dto);

    List<VacationMyHistoryDTO> findAllByEmployeeId(Long employeeId);


    Long save(HalfFormDTO dto);

    Long save(SickFormDTO dto, MultipartFile[] files);
}
