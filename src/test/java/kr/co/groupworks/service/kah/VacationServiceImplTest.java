package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.HalfFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.AmPm;
import kr.co.groupworks.entity.kah.VacationStatus;
import kr.co.groupworks.entity.kah.VacationType;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.repository.cis.EmployeeRepository;

import kr.co.groupworks.repository.kah.VacationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static kr.co.groupworks.entity.kah.QVacation.vacation;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class VacationServiceImplTest {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    static AnnualFormDTO annualFormDTO1;
    static AnnualFormDTO annualFormDTO2;
    static HalfFormDTO halfFormDTO1;

    @BeforeAll
    static void beforeAll() {
        annualFormDTO1 = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate(LocalDate.of(2500,6,22))
                .endDate(LocalDate.of(2500,8,22))
                .contents("Family vacation to Hawaii")
                .employeeId(1L)
                .build();

        // annualFormDTO1과 기간을 일부러 겹치게 설정
        annualFormDTO2 = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate(LocalDate.of(2500,6,22))
                .endDate(LocalDate.of(2500,12,22))
                .contents("Family asdlkfjlsdakj")
                .employeeId(1L)
                .build();

        halfFormDTO1 = kr.co.groupworks.dto.kah.HalfFormDTO.builder()
                .halfStartDate(LocalDate.of(2500,3,22))
                .halfContents("Family asdlkfjlsdakj")
                .amPm(AmPm.PM)
                .employeeId(1L)
                .build();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("연차 저장 테스트")
    void saveVacationAnnual() {

        //given
        Employee employee = employeeRepository.findByEmployeeId(1L);

        //when
        Long id = vacationService.save(annualFormDTO1);
        Vacation vacation = vacationRepository.findById(id).get();

        //then
        assertThat(vacation.getContents()).isEqualTo(annualFormDTO1.getContents());
        assertThat(vacation.getTitle()).isEqualTo("\uD83C\uDF34 연차");
        assertThat(vacation.getStatus()).isEqualTo(VacationStatus.PENDING);
        assertThat(vacation.getStartDate()).isEqualTo(String.valueOf(annualFormDTO1.getStartDate()));
        assertThat(vacation.getEndDate()).isEqualTo(String.valueOf(annualFormDTO1.getEndDate()));
        assertThat(vacation.getEmployee()).isEqualTo(employee);
    }

    @Test
    @DisplayName("연차 신청 내역 테스트")
    void selectVacationRequest(){
        vacationService.save(annualFormDTO1);
        vacationService.save(halfFormDTO1);

        List<VacationMyHistoryDTO> vacationRequestList = vacationService.findAllByEmployeeId(1L);

        assertThat(vacationRequestList).isNotNull();

    }

    @Test
    @DisplayName("반차 저장 테스트")
    void save() {
        //given
        Employee employee = employeeRepository.findByEmployeeId(1L);
        //when
        Long id = vacationService.save(halfFormDTO1);
        Vacation vacation = vacationRepository.findById(id).get();
        //then
        assertThat(vacation.getContents()).isEqualTo(halfFormDTO1.getHalfContents());
        assertThat(vacation.getTitle()).isEqualTo("\uD83D\uDD52 반차");
        assertThat(vacation.getStatus()).isEqualTo(VacationStatus.PENDING);
        assertThat(vacation.getStartDate()).isEqualTo(String.valueOf(halfFormDTO1.getHalfStartDate()));
        assertThat(vacation.getEmployee()).isEqualTo(employee);

    }


//    @AfterEach
//    void tearDown() {
//        employeeRepository.deleteById(1L);
//        employeeRepository.deleteById(2L);
//        employeeRepository.deleteById(3L);
//        employeeRepository.deleteById(4L);
//        employeeRepository.deleteById(5L);
//    }
}