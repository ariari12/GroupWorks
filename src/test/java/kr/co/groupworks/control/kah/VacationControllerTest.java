package kr.co.groupworks.control.kah;

import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.LeaveType;
import kr.co.groupworks.entity.kah.Vacation;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.service.kah.VacationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

    static AnnualFormDTO dummyData;
    static Employee employee1;
    static Employee employee2;
    static Employee employee3;
    static Employee employee4;
    static Employee employee5;

    @BeforeAll
    static void beforeAll() {
        employee1 = new Employee(1L, "password123", "John Doe", 101, "Manager", "Sales", 201, "john.doe@example.com", "123-456-7890", "123 Main St", "Male", LocalDateTime.of(2021, 1, 15, 0, 0), 60000, 301);
        employee2 = new Employee(2L, "password456", "Jane Smith", 102, "Senior Developer", "IT", 202, "jane.smith@example.com", "234-567-8901", "456 Oak St", "Female", LocalDateTime.of(2019, 3, 22, 0, 0), 75000, 302);
        employee3 = new Employee(3L, "password789", "Robert Brown", 103, "Analyst", "Finance", 203, "robert.brown@example.com", "345-678-9012", "789 Pine St", "Male", LocalDateTime.of(2020, 7, 1, 0, 0), 50000, 303);
        employee4 = new Employee(4L, "password101", "Emily White", 104, "HR Specialist", "HR", 204, "emily.white@example.com", "456-789-0123", "101 Maple St", "Female", LocalDateTime.of(2018, 11, 15, 0, 0), 55000, 304);
        employee5 = new Employee(5L, "password202", "Michael Johnson", 105, "Junior Developer", "IT", 202, "michael.johnson@example.com", "567-890-1234", "202 Birch St", "Male", LocalDateTime.of(2022, 5, 10, 0, 0), 45000, 302);



        dummyData = kr.co.groupworks.dto.kah.AnnualFormDTO.builder()
                .startDate("2024-07-01")
                .endDate("2024-07-10")
                .contents("Family vacation to Hawaii")
                .type(LeaveType.ANNUAL)
                .build();

    }

    @Test
    void vacationAnnual() {

        Vacation vacation = vacationService.save(dummyData, 1L);
        employeeRepository.save(employee1);

        Assertions.assertThat(vacation.getContents()).isEqualTo(dummyData.getContents());
        Assertions.assertThat(vacation.getTitle()).isEqualTo("연차");
        Assertions.assertThat(vacation.getStatus()).isEqualTo("검토중");
        Assertions.assertThat(vacation.getStartDate()).isEqualTo(dummyData.getStartDate());
        Assertions.assertThat(vacation.getEndDate()).isEqualTo(dummyData.getEndDate());
        Assertions.assertThat(vacation.getEndDate()).isEqualTo(dummyData.getEndDate());

    }
}