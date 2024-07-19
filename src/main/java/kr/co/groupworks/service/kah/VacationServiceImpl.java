package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.entity.kah.VacationStatus;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.kah.CalendarAttachmentRepository;
import kr.co.groupworks.repository.kah.VacationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService{
    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;
    private final CalendarAttachmentRepository calendarAttachmentRepository;


    // 연차 저장
    @Override
    public Vacation save(AnnualFormDTO dto, Long employeeId) {
        // 사원 엔티티 반환
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        log.info("employee = {}",employee);

        // 휴가 엔티티 변환
        Vacation vacation = Vacation.builder()
                .title("연차")
                .contents(dto.getContents())
                .startDate(String.valueOf(dto.getStartDate()))
                .endDate(String.valueOf(dto.getEndDate()))
                .status(VacationStatus.PENDING)
                .vacationType(dto.getType())
                .employee(employee)
                .build();
        return vacationRepository.save(vacation);
    }


    // 사원의 휴가신청 내역 모두 조회
    @Override
    public List<VacationMyHistoryDTO> findAllByEmployeeId(Long employeeId) {
        List<Vacation> vacationList = vacationRepository.findAllByEmployeeId(employeeId);
        // 휴가 번호로 첨부파일 조회
        return vacationList.stream()
                .map(vacation ->
                        VacationMyHistoryDTO.builder()
                                .startDate(vacation.getStartDate())
                                .endDate(vacation.getEndDate())
                                .vacationType(vacation.getVacationType())
                                .fileList(vacation.getAttachmentList())
                                .status(vacation.getStatus()).build())
                .toList();
    }
}
