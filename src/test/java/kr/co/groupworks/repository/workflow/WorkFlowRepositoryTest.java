package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.control.workflow.WorkFlowController;
import kr.co.groupworks.dto.workflow.vo.WorkflowListVO;
import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.workflow.ApproverEntity;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class WorkFlowRepositoryTest {
    @Autowired
    WorkFlowRepository workFlowRepository;

    @Autowired
    ApproversOnlyRepository approversOnlyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test @DisplayName("findByApproverEmployeeId Test")
    void findByApproverEmployeeId() {
        workFlowRepository.workflowListFindByApproverId(3L, 1)
                .forEach(entity -> log.info("Workflow: {}", entity.toString()));
    }

    @Test @DisplayName("WorkflowList Select Speed Test")
    public void WorkflowListSelectSpeedTest() {
        // Given
        long employeeId = 3L;
        StopWatch stopWatch = new StopWatch();

        // When
        stopWatch.start();
        Map<String, List<WorkflowListVO>> listMap = Map.of(
                WorkFlowController.AttributeName.APPROVAl.getStatus(), new ArrayList<>(),
                WorkFlowController.AttributeName.PROGRESS.getStatus(), new ArrayList<>(),
                WorkFlowController.AttributeName.REJECTION.getStatus(), new ArrayList<>()
        );

        workFlowRepository.workflowListFindByApproverId(employeeId, 1).forEach(list -> {
            for (ApproverEntity a : list.getApprovers()) {
                if(a.getEmployeeId() == employeeId) {
                    switch (a.getApproval()) {
                        case 1: listMap.get(WorkFlowController.AttributeName.APPROVAl.getStatus()).add(new WorkflowListVO(list)); break;
                        case 2: listMap.get(WorkFlowController.AttributeName.REJECTION.getStatus()).add(new WorkflowListVO(list)); break;
                        default: listMap.get(WorkFlowController.AttributeName.PROGRESS.getStatus()).add(new WorkflowListVO(list));
                    }
                }
            }
        });
        stopWatch.stop();

        // Then
        log.info(stopWatch.prettyPrint());
        listMap.forEach((key, value) -> {
            log.info("===== {} =====", key);
            for (WorkflowListVO workflowListVO : value) {
                log.info(workflowListVO.toString());
            }
        });
    }


    @BeforeEach
    @Test @DisplayName("Department, Employee Insert")
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
                .annualDaysUsed(i % 20) // annualDaysUsed: 연차 사용일 (0부터 19까지 반복)
                .sickDaysUsed(i % 15) // sickDaysUsed: 병가 사용일 (0부터 14까지 반복)
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
    }

    @Test @DisplayName("Workflow Entity Save Test")
    public void WorkflowEntitySaveTest() {
        // Given
        StopWatch stopWatch = new StopWatch();
        List<Employee> employeeList = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "employeeId"));
        employeeList.forEach(e -> log.info("{}", e));

        // Save Test Code
        stopWatch.start();

        // Create 10 WorkFlowEntity instances
        List<WorkFlowEntity> workflows = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Employee e = employeeList.get(i -1);
            WorkFlowEntity workflow = WorkFlowEntity.builder()
                    .employeeId(e.getEmployeeId())
                    .email(e.getEmail())
                    .phone(e.getPhoneNumber())
                    .employeeRank(e.getRankName())
//                    .departmentId(e.getDepartmentId())
//                    .department(e.getDepartmentName())
                    .employeeName(e.getEmployeeName())
                    .code("CODE" + i)
                    .workFlowType(i % 4 + 1) // Just an example to set type
                    .title("Title" + i)
                    .description("Description" + i)
                    .draftDate(LocalDateTime.now())
                    .approvalDate(LocalDateTime.now().plusDays(1))
                    .finalApprovalRank("FinalRank" + i)
                    .finalApprovalDepartment("FinalDept" + i)
                    .finalApprovalName("FinalName" + i)
                    .approverCount(10)
                    .approvalCount(i)
                    .cost(1000 * i)
                    .status(i % 3) // 0: 진행, 1: 승인, 2: 반려
                    .build();
            workflows.add(workflow);
        }
        List<WorkFlowEntity> savedWorkflows = workFlowRepository.saveAll(workflows);

        // 2. Save ApproverEntities
        for (int j = 0; j < 10; j++) {
            List<ApproverEntity> approvers = new ArrayList<>();
            int sequence = 1;
            for (int i = 1; i <= 10; i++) {
                sequence += i % 3 == 0 ? 1 : 0;
                Employee e = employeeList.get(i -1);
                ApproverEntity approver = ApproverEntity.builder()
                        .workFlowId(savedWorkflows.get(j).getId()) // Ensure valid workflow_id
                        .sequenceNum(sequence)
                        .approverType((i % 3) + 1)
                        .employeeId(e.getEmployeeId())
                        .approverEmail(e.getEmail())
                        .approverPhone(e.getPhoneNumber())
                        .approverName(e.getEmployeeName())
                        .approverRank(e.getRankName())
//                        .department(e.getDepartmentName())
                        .approvalMethod((i % 5) + 1)
                        .comment((i % 3) + 1 == 3 ? ("Comment approver " + i) : null)
                        .approvalDate((i % 3) + 1 == 1 ? LocalDateTime.now().plusDays(i) : null)
                        .approval(i % 3 +1 == 1 ? (j *i+5) %3 : 0)
                        .build();
                approvers.add(approver);
            }
            approversOnlyRepository.saveAll(approvers);
        }
        stopWatch.stop();

        // Then
        assertThat(savedWorkflows).hasSize(10);
        System.out.println(stopWatch.prettyPrint());
    }

    @Test @DisplayName("workflowApprovalSatisticsByDepartment Test")
    public void workflowApprovalSatisticsByDepartmentTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        workFlowRepository.workflowApprovalSatisticsByDepartment();
        stopWatch.stop();
        log.info("{}", stopWatch.prettyPrint());
    }

}