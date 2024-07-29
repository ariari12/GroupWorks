package kr.co.groupworks.service.workflow;

import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.dto.workflow.dto.ApproverDTO;
import kr.co.groupworks.dto.workflow.dto.WorkFlowDTO;
import kr.co.groupworks.dto.workflow.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.workflow.ApproversRepository;
import kr.co.groupworks.repository.workflow.WorkFlowRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@Transactional
class AppoverUpdateServiceImplTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    WorkFlowRepository workFlowRepository;
    @Autowired
    AppoverUpdateServiceImpl appoverUpdateService;
    @Autowired
    ApproversRepository approverRepository;

    @BeforeEach
    @Test @DisplayName("사원정보 insertSetUp Test")
    void insertSetUp() {
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

        // 출력 (테스트용)
        employees.forEach(employee ->
                System.out.println(employee.getEmployeeId() + ": " +
                        employee.getEmployeeName() + ", " +
                        employee.getEmail() + ", " +
                        employee.getPhoneNumber() + ", " +
                        employee.getDepartment().getDepartmentName())
        );

        employeeRepository.saveAll(employees);

        employees = employeeRepository.findAllById(List.of(2L, 3L, 4L, 5L, 6L));
        EmployeeDTO finalApprover01 = EmployeeDTO.entityToDto(employees.get(4));

        WorkFlowEntity workFlow01 = EmployeeDTO.entityToWorkflowDTO(Objects.requireNonNull(employeeRepository.findById(1L).orElse(null)))
                .setCode("01-t-010101")
                .setWorkFlowType(2)
                .setTitle("test01")
                .setDescription("Test Test Test")
                .setCost(0)
                .setDraftDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(ApproverDTO.STR_TO_LDT_PATTERN)))
                .setFinalApprovalRank(finalApprover01.getRank())
                .setFinalApprovalDepartment(finalApprover01.getDepartmentName())
                .setFinalApprovalName(finalApprover01.getName())

                .setApproverCount(0)
                .setApprovalCount(employees.size())
                .setStatus(3)
                .setAttachmentFiles(null)
                .dtoToEntity();
        WorkFlowDTO wd01 = WorkFlowDTO.entityToDto(workFlowRepository.save(workFlow01));
        log.info("wd: {}", wd01);
        workFlowRepository.save(wd01
                .setApprovers(employees.stream().map(e -> EmployeeDTO.entityToApproverDto(e)
                                .setWorkFlowId(wd01.getId()))
                        .toList())
                .dtoToEntity()
        );

        EmployeeDTO finalApprover02 = EmployeeDTO.entityToDto(employees.get(4));
        WorkFlowEntity workFlow02 = EmployeeDTO.entityToWorkflowDTO(Objects.requireNonNull(employeeRepository.findById(1L).orElse(null)))
                .setCode("02-t-020202")
                .setWorkFlowType(2)
                .setTitle("test02")
                .setDescription("Test Test Test")
                .setCost(0)
                .setDraftDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(ApproverDTO.STR_TO_LDT_PATTERN)))
                .setFinalApprovalRank(finalApprover02.getRank())
                .setFinalApprovalDepartment(finalApprover02.getDepartmentName())
                .setFinalApprovalName(finalApprover02.getName())

                .setApproverCount(0)
                .setApprovalCount(employees.size())
                .setStatus(0)
                .setAttachmentFiles(null)
                .dtoToEntity();
        WorkFlowDTO wd = WorkFlowDTO.entityToDto(workFlowRepository.save(workFlow02));
        log.info("wd: {}", wd);
        workFlowRepository.save(wd
                .setApprovers(employees.stream().map(e -> EmployeeDTO.entityToApproverDto(e)
                                .setWorkFlowId(wd.getId()).setApproval(1))
                        .toList())
                .dtoToEntity()
        );
    }

    @Test @DisplayName("Approver Update Test")
    public void approverUpdateTest() {
        Employee employee = employeeRepository.findById(2L).orElse(null);

        Employee testEmployee = Employee.builder()
                .employeeId(employee.getEmployeeId())
                .email("test@test.test")
                .phoneNumber("000-0000-0000")
//                .departmentName("전산")
                .rankName("이사")
                .employeeName(employee.getEmployeeName())
                .build();

        assertTrue(appoverUpdateService.setApproverUpdate(testEmployee));

        log.info("===== update =====");
        approverRepository.findByEmployeeId(employee.getEmployeeId())
                .forEach(a -> log.info("a: {}", ApproverDTO.entityToDto(a)));
    }

}