package kr.co.groupworks.workflow.repository;

import kr.co.groupworks.repository.cis.DepartmentRepository;
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
    DepartmentRepository departmentRepository;

    @Test
    void getDepartments() {
        departmentRepository.getDepartments().forEach(o -> log.info("{}", o));
    }
}