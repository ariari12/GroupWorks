package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.select.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.kah.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService{
    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;


    // 연차 저장
    @Override
    public Vacation save(AnnualFormDTO dto, Long employeeId) {
        // 사원 엔티티 반환
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        // 휴가 엔티티 변환
        Vacation vacation = Vacation.builder()
                .title("연차")
                .contents(dto.getContents())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status("검토중")
                .vacationType(dto.getType())
                .employee(employee)
                .build();
        return vacationRepository.save(vacation);
    }


    // 사원의 휴가신청 내역 모두 조회
    @Override
    public List<VacationMyHistoryDTO> findAllByEmployeeId(Long employeeId) {
        List<Vacation> vacationList = vacationRepository.findAllByEmployeeId(employeeId);
        return vacationList.stream()
                .map(vacation ->
                        new VacationMyHistoryDTO(
                                vacation.getEmployee().getEmployeeName(),
                                vacation.getStartDate(),
                                vacation.getEndDate(),
                                vacation.getVacationType(),
                                vacation.getFileName(),
                                vacation.getStatus()
                        ))
                .toList();
    }
}
