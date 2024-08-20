package kr.co.groupworks.service.cis;

import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.department.repository.DepartmentRepository;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test @DisplayName("Save Employee")
    void saveEmployee() {
        Department dept = departmentRepository.findAll().get(0);
        if(dept == null) {
            EmployeeDTO employee = EmployeeDTO.builder()
                    .employeePW("971120")
                    .employeeName("최일성")
                    .rankId(2)
                    .rankName("사원")
                    .department(dept.toDTO())
                    .email("dlftjd@gw.com")
                    .phoneNumber("010-1111-2222")
                    .address("서울시 노원구")
                    .gender("Male")
                    .joinDate(LocalDateTime.now())
                    .salary(65000L)
                    .supervisorId(1033L)
                    .build();

            employeeService.saveEmployee(employee);
            log.info("saveEmployee Test Success!");
        }
    }
}
