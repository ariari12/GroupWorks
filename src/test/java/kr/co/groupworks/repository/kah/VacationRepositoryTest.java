//package kr.co.groupworks.repository.kah;
//
//import kr.co.groupworks.calendar.dto.AnnualFormDTO;
//import kr.co.groupworks.calendar.entity.Vacation;
//import kr.co.groupworks.calendar.repository.VacationRepository;
//import kr.co.groupworks.common.mapper.VacationMapper;
//import kr.co.groupworks.employee.repository.EmployeeRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
//
//@SpringBootTest
//@Transactional
//class VacationRepositoryTest {
//    @Autowired
//    private VacationRepository vacationRepository;
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Autowired
//    private VacationMapper vacationMapper;
//
//    static AnnualFormDTO annualFormDTO1;
//
//    @BeforeAll
//    static void beforeAll() {
//        annualFormDTO1 = AnnualFormDTO.builder()
//                .startDate(LocalDate.of(2500,6,22))
//                .endDate(LocalDate.of(2500,8,22))
//                .contents("Family vacation to Hawaii")
//                .build();
//
//    }
//
//    @BeforeEach
//    void setUp() {
//        Vacation entity = vacationMapper.toEntity(annualFormDTO1, employeeRepository.findById(1L).get());
//        vacationRepository.save(entity);
//    }
//
//    @Test
//    @DisplayName("휴가 기간 중복 테스트")
//    void findOverlappingVacations() {
//        List<Vacation> overlappingVacations = vacationRepository.findOverlappingVacations(1L, "2500-06-22", "2500-08-22");
//
//        assertThat(overlappingVacations).isNotEmpty();
//
//    }
//}