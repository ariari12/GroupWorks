package kr.co.groupworks.service.workflow;

import kr.co.groupworks.dto.workflow.dto.ApproverDTO;
import kr.co.groupworks.dto.workflow.dto.WorkFlowDTO;
import kr.co.groupworks.dto.workflow.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.workflow.ApproversOnlyRepository;
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
    ApproversOnlyRepository approverRepository;

    @BeforeEach
    @Test @DisplayName("사원정보 insertSetUp Test")
    void insertSetUp() {
        List<Employee> employees = List.of(
                Employee.builder().employeeId(1L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("김철수").rankId(1).rankName("사원").departmentId(1).departmentName("기술부서")
                        .email("john.doe@example.com").phoneNumber("555-1234").address("123 Main St").gender("Male")
                        .joinDate(LocalDateTime.of(2023, 1, 15, 9, 0)).salary(50000L).supervisorId(null).build(),
                Employee.builder().employeeId(2L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("이영희").rankId(2).rankName("주임").departmentId(2).departmentName("마케팅부서")
                        .email("jane.smith@example.com").phoneNumber("555-5678").address("456 Elm St").gender("Female")
                        .joinDate(LocalDateTime.of(2022, 3, 12, 9, 0)).salary(60000L).supervisorId(1L).build(),
                Employee.builder().employeeId(3L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("박민수").rankId(3).rankName("책임").departmentId(3).departmentName("인사부서")
                        .email("mike.jones@example.com").phoneNumber("555-9012").address("789 Oak St").gender("Male")
                        .joinDate(LocalDateTime.of(2021, 6, 25, 9, 0)).salary(70000L).supervisorId(2L).build(),
                Employee.builder().employeeId(4L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("최수연").rankId(2).rankName("대리").departmentId(1).departmentName("기술부서")
                        .email("susan.brown@example.com").phoneNumber("555-3456").address("101 Pine St").gender("Female")
                        .joinDate(LocalDateTime.of(2023, 2, 10, 9, 0)).salary(55000L).supervisorId(3L).build(),
                Employee.builder().employeeId(5L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("정우성").rankId(1).rankName("과장").departmentId(2).departmentName("마케팅부서")
                        .email("tom.wilson@example.com").phoneNumber("555-7890").address("202 Cedar St").gender("Male")
                        .joinDate(LocalDateTime.of(2020, 11, 30, 9, 0)).salary(45000L).supervisorId(4L).build(),
                Employee.builder().employeeId(6L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("신혜진").rankId(2).rankName("차장").departmentId(3).departmentName("인사부서")
                        .email("lisa.white@example.com").phoneNumber("555-1235").address("303 Birch St").gender("Female")
                        .joinDate(LocalDateTime.of(2019, 5, 20, 9, 0)).salary(65000L).supervisorId(5L).build(),
                Employee.builder().employeeId(7L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("오준호").rankId(3).rankName("부장").departmentId(1).departmentName("기술부서")
                        .email("daniel.green@example.com").phoneNumber("555-5679").address("404 Spruce St").gender("Male")
                        .joinDate(LocalDateTime.of(2018, 4, 15, 9, 0)).salary(80000L).supervisorId(6L).build(),
                Employee.builder().employeeId(8L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("강하늘").rankId(3).rankName("이사").departmentId(2).departmentName("마케팅부서")
                        .email("nancy.black@example.com").phoneNumber("555-9013").address("505 Maple St").gender("Female")
                        .joinDate(LocalDateTime.of(2017, 7, 19, 9, 0)).salary(75000L).supervisorId(7L).build(),
                Employee.builder().employeeId(9L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("권민지").rankId(1).rankName("상무").departmentId(3).departmentName("인사부서")
                        .email("peter.gray@example.com").phoneNumber("555-3457").address("606 Redwood St").gender("Male")
                        .joinDate(LocalDateTime.of(2022, 8, 23, 9, 0)).salary(48000L).supervisorId(8L).build(),
                Employee.builder().employeeId(10L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("백승훈").rankId(2).rankName("전무").departmentId(1).departmentName("기술부서")
                        .email("emma.purple@example.com").phoneNumber("555-7891").address("707 Cherry St").gender("Female")
                        .joinDate(LocalDateTime.of(2023, 3, 18, 9, 0)).salary(62000L).supervisorId(9L).build()
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
                .departmentName("전산")
                .rankName("이사")
                .employeeName(employee.getEmployeeName())
                .build();

        assertTrue(appoverUpdateService.setApproverUpdate(testEmployee));

        log.info("===== update =====");
        approverRepository.findByEmployeeId(employee.getEmployeeId())
                .forEach(a -> log.info("a: {}", ApproverDTO.entityToDto(a)));
    }

}