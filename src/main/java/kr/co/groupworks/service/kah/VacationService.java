package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VacationService {
    Long save(AnnualFormDTO dto);

    List<VacationMyRequestDTO> findAllByEmployeeId(Long employeeId);


    Long save(HalfFormDTO dto);

    Long save(SickFormDTO dto, MultipartFile[] files);

    Long save(OtherFormDTO dto, MultipartFile[] files);

    List<VacationMyHistoryDTO> findVacationHistory(Long employeeId);
}
