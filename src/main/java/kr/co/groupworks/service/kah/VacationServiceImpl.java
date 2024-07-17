package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.repository.kah.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService{
    private final VacationRepository vacationRepository;
    @Override
    public Vacation save(AnnualFormDTO dto, Long employeeId) {

        // 휴가 엔티티 변환
        Vacation vacation = Vacation.builder()
                .title("연차")
                .contents(dto.getContents())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status("검토중")
                .vacationType(dto.getType())
                .build();
        return vacationRepository.save(vacation);
    }
}
