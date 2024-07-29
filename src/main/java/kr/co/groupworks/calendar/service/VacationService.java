package kr.co.groupworks.calendar.service;

import kr.co.groupworks.calendar.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VacationService {
    Long save(AnnualFormDTO dto);

    List<VacationMyRequestDTO> findAllByEmployeeId(Long employeeId);


    Long save(HalfFormDTO dto);

    Long save(SickFormDTO dto, MultipartFile[] files);

    Long save(OtherFormDTO dto, MultipartFile[] files);

    List<VacationMyHistoryDTO> findVacationHistory(Long employeeId);

    void deleteRequest(Long calendarId, Long employeeId);
}
