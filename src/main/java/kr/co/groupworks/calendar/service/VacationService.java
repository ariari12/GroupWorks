package kr.co.groupworks.calendar.service;

import kr.co.groupworks.calendar.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VacationService {
    Long save(AnnualFormDTO dto);

    List<VacationRequestDTO> findAllByEmployeeId(Long employeeId);


    Long save(HalfFormDTO dto);

    Long save(SickFormDTO dto, MultipartFile[] files);

    Long save(OtherFormDTO dto, MultipartFile[] files);

    List<VacationMyHistoryDTO> findVacationHistory(Long employeeId);

    void deleteRequest(Long calendarId, Long employeeId);

    VacationModifyFormDTO findCalendarByIdAndEmployee(Long calendarId, Long employeeId);

    void modifyVacation(Long calendarId, VacationModifyFormDTO dto, Long employeeId, MultipartFile[] files);

    List<CalendarFormDTO> findAllVacation(Long employeeId);

    Page<VacationRequestDTO> findAllTeam(Long employeeId, Pageable pageable);
}
