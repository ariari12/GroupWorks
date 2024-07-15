package kr.co.groupworks.repository.ljm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class WorkFlowRepositoryTest {
    @Autowired
    WorkFlowRepository workFlowRepository;

    @Test @DisplayName("findByApproverEmployeeId Test")
    void findByApproverEmployeeId() {
        workFlowRepository.findByApproverEmployeeId(1)
                .forEach(entity -> log.info("Workflow: {}", entity.toString()));
    }

}