package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.dto.ApproverDTO;
import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@Transactional
class WorkFlowServiceImplTest {
    @Autowired
    WorkFlowServiceImpl workFlowService;

    @Test @DisplayName("Employee Select Test")
    public void employeeSelectTest() {
        log.info("employeeSelectTest employee: {}", workFlowService.getEmployeeDTO(1));
    }

    @Test @DisplayName("Employee All Select Test")
    public void employeeAllSelectTest() {
        for (EmployeeDTO employeeDTO : workFlowService.getEmployeeAllDTOList()) {
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
        workFlowService.setAttachmentFileList(files, 1L, "testUser");
    }

    @Test @DisplayName("ApproverList Insert Test")
    public void approverListInsertTest() {
        List<ApproverDTO> approverDTOList = new ArrayList<>();
        Map<String, Integer[]> approverMap = new HashMap<>();
        approverMap.put("approve", new Integer[]{4, 7, 10});
        approverMap.put("collaborate", new Integer[]{2, 5, 8});
        approverMap.put("refer", new Integer[]{3, 6, 9});

        for (int i = 1; i < approverMap.get("approve").length; i++) {
            approverDTOList.add(
                    workFlowService.getApproverDTO(approverMap.get("approve")[i -1])
                            .setWorkFlowId(1L)
                            .setSequenceNum(i)
                            .setApproverType(1)
            );
        }
        for (int i = 0; i < approverMap.get("collaborate").length; i++) {
            approverDTOList.add(
                    workFlowService.getApproverDTO(approverMap.get("collaborate")[i])
                            .setWorkFlowId(1L)
                            .setSequenceNum(i)
                            .setApproverType(2)
            );
        }
        for (int i = 0; i < approverMap.get("refer").length; i++) {
            approverDTOList.add(
                    workFlowService.getApproverDTO(approverMap.get("refer")[i])
                            .setWorkFlowId(1L)
                            .setSequenceNum(i)
                            .setApproverType(3)
            );
        }
        workFlowService.setApproverDTOList(approverDTOList);
    }

    @Test @DisplayName("Workflow List Test")
    public void workflowListTest() {
        workFlowService.getMyWorkFlowDTOList(1L).forEach((s, i) -> {
            log.info("{} list length: {}", s, i.size());
        });
    }

    @Test @DisplayName("Approval Wait List Select Test")
    public void approvalWaitListSelectTest() {
        log.info("Approval Wait List Select Test");
        workFlowService.getWorkflowWaitList(4L).forEach((s, l) -> {
            l.forEach(i -> log.info("{}: {}", s, i));
        });
    }

    @Test @DisplayName("LDT Now String Fomatt Test")
    public void ldtNowStringFomattTest() {
        String approvalNow = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").format(LocalDateTime.now());
        log.info("LDT Now String Fomatt Test: {}", approvalNow);
    }

}