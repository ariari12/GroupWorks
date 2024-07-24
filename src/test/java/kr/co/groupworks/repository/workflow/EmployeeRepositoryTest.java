package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void getDepartments() {
        employeeRepository.getDepartments().forEach((l, d) -> log.info("{}: {}", l, d));
    }
}