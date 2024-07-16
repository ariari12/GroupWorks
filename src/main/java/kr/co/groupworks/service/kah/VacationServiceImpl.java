package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.entity.kah.Calendar;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.repository.kah.CalendarRepository;
import kr.co.groupworks.repository.kah.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService{
    private final VacationRepository vacationRepository;
    private final CalendarRepository calendarRepository;
    @Override
    public void save(AnnualFormDTO dto, Long employeeId) {
        // 캘린더 엔티티 변환
        Calendar calendar = Calendar.builder()
                .contents(dto.getContents())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        calendarRepository.save(calendar);
        // 휴가 엔티티 변환
        Vacation vacation = Vacation.builder()
                .calendar(calendar)
                .status("검토중")
                .vacationType(String.valueOf(dto.getType()))
                .build();
        vacationRepository.save(vacation);
    }
}
