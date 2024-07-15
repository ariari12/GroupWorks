package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@SpringBootTest
class WorkFlowServiceImplTest {
    @Autowired
    WorkFlowServiceImpl workFlowService;

    @Test @DisplayName("Employee Select Test")
    public void employeeSelectTest() {
        log.info("employeeSelectTest employee: {}", workFlowService.getEmployee(1));
    }

    @Test @DisplayName("Employee All Select Test")
    public void employeeAllSelectTest() {
        for (EmployeeDTO employeeDTO : workFlowService.getEmployeeAll()) {
            log.info("employeeAllSelectTest employee: {}", employeeDTO);
        }
    }

    /* FIle Upload Test */
    @Test @DisplayName("setAttachmentFileList Test")
    public void setAttachmentFileListTest() {
        log.info("setAttachmentFileList Test");

        // 테스트용 MultipartFile 배열 생성
        MultipartFile[] files = new MultipartFile[2];

        // 첫 번째 파일 생성
        String fileName1 = "testfile1.txt";
        byte[] content1 = "Hello, World!".getBytes();
        files[0] = new MockMultipartFile(fileName1, fileName1, "text/plain", content1);

        // 두 번째 파일 생성
        String fileName2 = "testfile2.txt";
        byte[] content2 = "Spring Boot File Upload Example".getBytes();
        files[1] = new MockMultipartFile(fileName2, fileName2, "text/plain", content2);

        // 서비스 메서드 호출
        workFlowService.setAttachmentFileList(files);
    }

}