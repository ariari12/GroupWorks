package kr.co.groupworks.workflow.service;

import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.department.repository.DepartmentRepository;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.workflow.dto.dto.ApproverDTO;
import kr.co.groupworks.workflow.dto.dto.WorkFlowDTO;
import kr.co.groupworks.workflow.dto.employee.EmployeeDTO;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;
import kr.co.groupworks.workflow.repository.ApproversRepository;
import kr.co.groupworks.workflow.repository.WorkFlowRepository;
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
    DepartmentRepository departmentRepository;
    @Autowired
    WorkFlowRepository workFlowRepository;
    @Autowired
    AppoverUpdateServiceImpl appoverUpdateService;
    @Autowired
    ApproversRepository approverRepository;

    private List<Employee> employees;

    @BeforeEach @DisplayName("사원정보 insertSetUp Test")
    void insertSetUp() {
        // 샘플 부서 데이터
        List<Department> departments = List.of(
                new Department(0L, "기술부서", "010-1234-1111", "A동"),
                new Department(0L, "마케팅부서", "010-1234-2222", "B동"),
                new Department(0L, "영업부서", "010-1234-3333", "C동"),
                new Department(0L, "인사부서", "010-1234-4444", "D동"),
                new Department(0L, "재무부서", "010-1234-5555", "E동"),
                new Department(0L, "IT 지원부서", "010-1234-6666", "F동"),
                new Department(0L, "제품 관리부서", "010-1234-7777", "G동"),
                new Department(0L, "고객 서비스부서", "010-1234-8888", "H동"),
                new Department(0L, "법무부서", "010-1234-9999", "I동"),
                new Department(0L, "연구개발부서", "010-1234-1010", "J동")
        );
        // 샘플 사원 데이터 생성
        List<Department> finalDepartments = departmentRepository.saveAll(departments);
        employees = IntStream.rangeClosed(1, 10).mapToObj(i -> Employee.builder()
                .employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO") // employeePW: 1111
                .employeeName("사원" + i) // employeeName: 사원 이름 (사원1, 사원2, ..., 사원100)
                .rankId((i % 5) + 1)    // rankId: 사원 직급 ID (1부터 5까지 반복)
                .rankName("직급" + (i % 5 + 1)) // rankName: 사원 직급 이름 (직급1, 직급2, ..., 직급5 반복)
                .department(finalDepartments.get(i % finalDepartments.size())) // department: 부서 (부서 목록에서 순환 선택)
                .email("apporverUdate" + i + "@test.com")
                .phoneNumber("010-1111-111" + (i % 10)) // phoneNumber: 사원 전화번호 (010-1111-1110, 010-1111-1111, ..., 010-1111-1119 반복)
                .address("주소 " + i) // address: 사원 주소 (주소 1, 주소 2, ..., 주소 100)
                .gender((i % 2 == 0) ? "남" : "여") // gender: 사원 성별 (남 또는 여, 홀수는 여, 짝수는 남)
                .joinDate(LocalDateTime.now().minusDays(i)) // joinDate: 입사일 (현재 날짜에서 i일 전)
                .salary((3000L + i) * 10L) // salary: 사원 급여 (3000부터 시작, i에 따라 증가)
                .supervisorId(0L) // supervisorId: 상사 ID (1부터 10까지 반복)
                .build()
        ).toList();
        employees = employeeRepository.saveAll(employees);

        // 출력 (확인 용)
        employees.forEach(employee ->
                System.out.println(employee.getEmployeeId() + ": " +
                        employee.getEmployeeName() + ", " +
                        employee.getEmail() + ", " +
                        employee.getPhoneNumber() + ", " +
                        employee.getDepartment().getDepartmentName())
        );
        long employeeId01 = employees.get(0).getEmployeeId();

        EmployeeDTO finalApprover01 = EmployeeDTO.entityToDto(employees.get(4));
        WorkFlowEntity workFlow01 = EmployeeDTO.entityToWorkflowDTO(Objects.requireNonNull(employeeRepository.findById(employeeId01).orElse(null)))
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

        log.info("wd01: {}", wd01);
        workFlowRepository.save(wd01
                .setApprovers(employees.stream()
                        .map(e -> EmployeeDTO.entityToApproverDto(e).setWorkFlowId(wd01.getId()))
                        .toList())
                .dtoToEntity()
        );

        EmployeeDTO finalApprover02 = EmployeeDTO.entityToDto(employees.get(4));
        WorkFlowEntity workFlow02 = EmployeeDTO.entityToWorkflowDTO(Objects.requireNonNull(employeeRepository.findById(employeeId01).orElse(null)))
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
        log.info("wd02: {}", wd);
        workFlowRepository.save(wd
                .setApprovers(employees.stream()
                        .map(e -> EmployeeDTO.entityToApproverDto(e).setWorkFlowId(wd.getId()).setApproval(1))
                        .toList())
                .dtoToEntity()
        );
    }

    @Test @DisplayName("Approver Update Test")
    public void approverUpdateTest() {
        employees.forEach(e -> log.info("{}", e));

        Employee employee = employees.get(0);
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