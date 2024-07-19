package kr.co.groupworks.service.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.VacationStatus;
import kr.co.groupworks.entity.kah.VacationType;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.repository.cis.EmployeeRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class VacationServiceImplTest {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private EmployeeRepository employeeRepository;

    static AnnualFormDTO annualFormDTO1;
    static AnnualFormDTO annualFormDTO2;

    @BeforeAll
    static void beforeAll() {
        annualFormDTO1 = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate(LocalDate.of(2500,6,22))
                .endDate(LocalDate.of(2500,8,22))
                .contents("Family vacation to Hawaii")
                .type(VacationType.ANNUAL)
                .build();

        annualFormDTO2 = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate(LocalDate.of(2500,10,22))
                .endDate(LocalDate.of(2500,12,22))
                .contents("Family asdlkfjlsdakj")
                .type(VacationType.ANNUAL)
                .build();
    }

    @BeforeEach
    void setUp() {

//        Employee employee1;
//        Employee employee2;
//        Employee employee3;
//        Employee employee4;
//        Employee employee5;

//        employee1 = new Employee(1L, "password123", "John Doe", 101, "Manager", "Sales", 201, "john.doe@example.com", "123-456-7890", "123 Main St", "Male", LocalDateTime.of(2021, 1, 15, 0, 0), 60000, 301);
//        employee2 = new Employee(2L, "password456", "Jane Smith", 102, "Senior Developer", "IT", 202, "jane.smith@example.com", "234-567-8901", "456 Oak St", "Female", LocalDateTime.of(2019, 3, 22, 0, 0), 75000, 302);
//        employee3 = new Employee(3L, "password789", "Robert Brown", 103, "Analyst", "Finance", 203, "robert.brown@example.com", "345-678-9012", "789 Pine St", "Male", LocalDateTime.of(2020, 7, 1, 0, 0), 50000, 303);
//        employee4 = new Employee(4L, "password101", "Emily White", 104, "HR Specialist", "HR", 204, "emily.white@example.com", "456-789-0123", "101 Maple St", "Female", LocalDateTime.of(2018, 11, 15, 0, 0), 55000, 304);
//        employee5 = new Employee(5L, "password202", "Michael Johnson", 105, "Junior Developer", "IT", 202, "michael.johnson@example.com", "567-890-1234", "202 Birch St", "Male", LocalDateTime.of(2022, 5, 10, 0, 0), 45000, 302);

//        employeeRepository.save(employee1);
//        employeeRepository.save(employee2);
//        employeeRepository.save(employee3);
//        employeeRepository.save(employee4);
//        employeeRepository.save(employee5);



    }

    // 연차 저장 테스트
    @Test
    void saveVacationAnnual() {
        Employee employee = employeeRepository.findByEmployeeId(1L);
        Vacation vacation = vacationService.save(annualFormDTO1,1L);


        assertThat(vacation.getContents()).isEqualTo(annualFormDTO1.getContents());
        assertThat(vacation.getTitle()).isEqualTo("연차");
        assertThat(vacation.getStatus()).isEqualTo(VacationStatus.PENDING);
        assertThat(vacation.getStartDate()).isEqualTo(String.valueOf(annualFormDTO1.getStartDate()));
        assertThat(vacation.getEndDate()).isEqualTo(String.valueOf(annualFormDTO1.getEndDate()));
        assertThat(vacation.getEmployee()).isEqualTo(employee);

    }

    //연차 신청 내역 테스트
    @Test
    void selectVacationRequest(){
        vacationService.save(annualFormDTO1, 1L);
        vacationService.save(annualFormDTO2, 1L);

        List<VacationMyHistoryDTO> vacationRequestList = vacationService.findAllByEmployeeId(1L);

        assertThat(vacationRequestList).isNotNull();

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