package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Test
    void saveEmployee() {
        EmployeeDTO employee = EmployeeDTO.builder()
                .employeeId(2106L)
                .employeePW("971120")
                .employeeName("최일성")
                .rankId(2)
                .rankName("사원")
                .departmentId(10L)
                .departmentName("IT")
                .email("dlftjd@gw.com")
                .phoneNumber("010-1111-2222")
                .address("서울시 노원구")
                .gender("Male")
                .joinDate(LocalDateTime.now())
                .salary(65000L)
                .supervisorId(1033L)
                .build();

        employeeService.saveEmployee(employee);
    }
}