package kr.co.groupworks.control.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.LeaveType;
import kr.co.groupworks.entity.kah.Calendar;
import kr.co.groupworks.repository.kah.CalendarRepository;
import kr.co.groupworks.repository.kah.VacationRepository;
import kr.co.groupworks.service.kah.VacationService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationControllerTest {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private CalendarRepository calendarRepository;
    static AnnualFormDTO dummyData;

    @BeforeAll
    static void beforeAll() {
        dummyData = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate("2024-07-01")
                .endDate("2024-07-10")
                .contents("Family vacation to Hawaii")
                .build();

    }

    @Test
    @Transactional
    void vacationAnnual() {
        vacationService.save(dummyData,1000L);

        Calendar calendar = calendarRepository.findById(1L).get();

        Assertions.assertThat(calendar.getContents()).isEqualTo(dummyData.getContents());
        Assertions.assertThat(calendar.getStartDate()).isEqualTo(dummyData.getStartDate());
        Assertions.assertThat(calendar.getEndDate()).isEqualTo(dummyData.getEndDate());

    }
}