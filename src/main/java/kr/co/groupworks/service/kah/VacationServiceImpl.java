package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.HalfFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.entity.kah.VacationStatus;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.kah.CalendarAttachmentRepository;
import kr.co.groupworks.repository.kah.VacationRepository;
import kr.co.groupworks.util.mapper.VacationMapper;
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
    private final VacationMapper vacationMapper;
    private final CalendarAttachmentRepository calendarAttachmentRepository;


    // 연차 저장
    @Override
    public Vacation save(AnnualFormDTO dto) {
        // 사원 엔티티 반환
        Employee employee = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        log.info("employee = {}",employee);

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getStartDate()), String.valueOf(dto.getEndDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
        return vacationRepository.save(vacation);
    }

    // 반차 저장
    @Override
    public Vacation save(HalfFormDTO dto) {
        // 사원 엔티티 반환
        Employee employee = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        log.info("employee = {}",employee);

        // 기간이 겹치는 휴가가 있는지 확인
        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(
                dto.getEmployeeId(), String.valueOf(dto.getHalfStartDate()), String.valueOf(dto.getHalfStartDate()));

        if (!overlappingVacations.isEmpty()) {
            throw new IllegalArgumentException("겹치는 휴가기간이 있습니다.");
        }

        // 휴가 엔티티 변환
        Vacation vacation = vacationMapper.toEntity(dto,employee);
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
                                .endDate(vacation.getEndDate() != null ? vacation.getEndDate() : vacation.getStartDate())
                                .vacationType(vacation.getVacationType())
                                .fileList(vacation.getAttachmentList())
                                .status(vacation.getStatus()).build())
                .toList();
    }


}
