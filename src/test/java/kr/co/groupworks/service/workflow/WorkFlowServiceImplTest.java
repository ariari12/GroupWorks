package kr.co.groupworks.service.workflow;

import kr.co.groupworks.dto.workflow.dto.ApproverDTO;
import kr.co.groupworks.dto.workflow.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
@Transactional
class WorkFlowServiceImplTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    WorkFlowServiceImpl workFlowService;

    @BeforeEach
    @DisplayName("Department, Employee Insert")
    public void insetTest() {
        // 샘플 부서 데이터
        List<Department> departments = List.of(
                new Department(1L, "기술부서", "010-1234-5678", "A동"),
                new Department(2L, "마케팅부서", "010-1234-5679", "B동"),
                new Department(3L, "영업부서", "010-1234-5680", "C동"),
                new Department(4L, "인사부서", "010-1234-5681", "D동"),
                new Department(5L, "재무부서", "010-1234-5682", "E동"),
                new Department(6L, "IT 지원부서", "010-1234-5683", "F동"),
                new Department(7L, "제품 관리부서", "010-1234-5684", "G동"),
                new Department(8L, "고객 서비스부서", "010-1234-5685", "H동"),
                new Department(9L, "법무부서", "010-1234-5686", "I동"),
                new Department(10L, "연구개발부서", "010-1234-5687", "J동")
        );

        // 샘플 사원 데이터 생성
        List<Employee> employees = IntStream.rangeClosed(1, 100).mapToObj(i -> Employee.builder()
                .employeeId((long) i)
                // PW : 1111
                .employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO") // employeePW: 사원 비밀번호 (pw1, pw2, ..., pw100)
                .employeeName("사원" + i) // employeeName: 사원 이름 (사원1, 사원2, ..., 사원100)
                .rankId((i % 5) + 1)    // rankId: 사원 직급 ID (1부터 5까지 반복)
                .rankName("직급" + (i % 5 + 1)) // rankName: 사원 직급 이름 (직급1, 직급2, ..., 직급5 반복)
                .department(departments.get(i % departments.size())) // department: 부서 (부서 목록에서 순환 선택)
                .email("employee" + i + "@example.com") // email: 사원 이메일 (employee1@example.com, employee2@example.com, ..., employee100@example.com)
                .phoneNumber("010-1111-111" + (i % 10)) // phoneNumber: 사원 전화번호 (010-1111-1110, 010-1111-1111, ..., 010-1111-1119 반복)
                .address("주소 " + i) // address: 사원 주소 (주소 1, 주소 2, ..., 주소 100)
                .gender((i % 2 == 0) ? "남" : "여") // gender: 사원 성별 (남 또는 여, 홀수는 여, 짝수는 남)
                .joinDate(LocalDateTime.now().minusDays(i)) // joinDate: 입사일 (현재 날짜에서 i일 전)
                .salary((3000L + i) * 10L) // salary: 사원 급여 (3000부터 시작, i에 따라 증가)
                .supervisorId(i > 10 ? (long) ((i % 10) + 1) : 0) // supervisorId: 상사 ID (1부터 10까지 반복)
                .build()
        ).toList();

        employees = employeeRepository.saveAll(employees);

        // 출력 (테스트용)
        employees.forEach(employee ->
                System.out.println(employee.getEmployeeId() + ": " +
                        employee.getEmployeeName() + ", " +
                        employee.getEmail() + ", " +
                        employee.getPhoneNumber() + ", " +
                        employee.getDepartment().getDepartmentName())
        );

    }

    @Test @DisplayName("Employee Select Test")
    public void employeeSelectTest() {
        log.info("employeeSelectTest employee: {}", workFlowService.getEmployeeDTO(1L));
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

    @Test @DisplayName("Statistics Test")
    public void statisticsTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object res = workFlowService.getWorkflowStatistics(1L, 3L, 5);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("res: {}", res.toString());
    }
}