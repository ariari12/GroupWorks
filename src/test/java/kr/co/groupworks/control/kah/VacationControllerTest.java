package kr.co.groupworks.control.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.VacationType;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.service.kah.VacationService;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class VacationControllerTest {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private EmployeeRepository employeeRepository;




    @BeforeAll
    void beforeAll() {
        AnnualFormDTO dummyData1;
        AnnualFormDTO dummyData2;
        Employee employee1;
        Employee employee2;
        Employee employee3;
        Employee employee4;
        Employee employee5;

        employee1 = new Employee(1L, "password123", "John Doe", 101, "Manager", "Sales", 201, "john.doe@example.com", "123-456-7890", "123 Main St", "Male", LocalDateTime.of(2021, 1, 15, 0, 0), 60000, 301);
        employee2 = new Employee(2L, "password456", "Jane Smith", 102, "Senior Developer", "IT", 202, "jane.smith@example.com", "234-567-8901", "456 Oak St", "Female", LocalDateTime.of(2019, 3, 22, 0, 0), 75000, 302);
        employee3 = new Employee(3L, "password789", "Robert Brown", 103, "Analyst", "Finance", 203, "robert.brown@example.com", "345-678-9012", "789 Pine St", "Male", LocalDateTime.of(2020, 7, 1, 0, 0), 50000, 303);
        employee4 = new Employee(4L, "password101", "Emily White", 104, "HR Specialist", "HR", 204, "emily.white@example.com", "456-789-0123", "101 Maple St", "Female", LocalDateTime.of(2018, 11, 15, 0, 0), 55000, 304);
        employee5 = new Employee(5L, "password202", "Michael Johnson", 105, "Junior Developer", "IT", 202, "michael.johnson@example.com", "567-890-1234", "202 Birch St", "Male", LocalDateTime.of(2022, 5, 10, 0, 0), 45000, 302);


        dummyData1 = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate("2024-07-01")
                .endDate("2024-07-10")
                .contents("Family vacation to Hawaii")
                .type(VacationType.ANNUAL)
                .build();

        dummyData2 = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate("2024-08-01")
                .endDate("2024-08-10")
                .contents("Family asdlkfjlsdakj")
                .type(VacationType.ANNUAL)
                .build();

    }


//    // 연차 저장 테스트
//    @Test
//    void saveVacationAnnual() {
//
//        Vacation vacation = vacationService.save(dummyData1, employee1.getEmployeeId());
//        Employee employee = employeeRepository.findByEmployeeId(employee1.getEmployeeId());
//
//        assertThat(vacation.getContents()).isEqualTo(dummyData1.getContents());
//        assertThat(vacation.getTitle()).isEqualTo("연차");
//        assertThat(vacation.getStatus()).isEqualTo("검토중");
//        assertThat(vacation.getStartDate()).isEqualTo(dummyData1.getStartDate());
//        assertThat(vacation.getEndDate()).isEqualTo(dummyData1.getEndDate());
//        assertThat(vacation.getEmployee()).isEqualTo(employee);
//
//    }
//
//    //연차 신청 내역 테스트
//    @Test
//    @Transactional(readOnly = false)
//    void selectVacationRequest(){
//        Employee savedEmployee1 = employeeRepository.save(employee1);
//        vacationService.save(dummyData1, savedEmployee1.getEmployeeId());
//        vacationService.save(dummyData2, savedEmployee1.getEmployeeId());
//
//        List<VacationMyHistoryDTO> vacationRequestList = vacationService.findAllByEmployeeId(employee1.getEmployeeId());
//
//        assertThat(vacationRequestList).isNotNull();
//        assertThat(vacationRequestList).hasSize(2);
//
//    }
}