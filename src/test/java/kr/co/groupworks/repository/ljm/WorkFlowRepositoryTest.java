package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.control.ljm.WorkFlowController;
import kr.co.groupworks.dto.ljm.vo.WorkflowListVO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.ljm.ApproverEntity;
import kr.co.groupworks.entity.ljm.WorkFlowEntity;
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
    @Test @DisplayName("사원정보 insertSetUp Test")
    void insertSetUp() {
        List<Employee> employees = List.of(
                Employee.builder().employeeId(1L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("김철수").rankId(1).rankName("사원").departmentId(1).departmentName("기술부서")
                        .email("john.doe@example.com").phoneNumber("555-1234").address("123 Main St").gender("Male")
                        .joinDate(LocalDateTime.of(2023, 1, 15, 9, 0)).salary(50000).supervisorId(null).build(),
                Employee.builder().employeeId(2L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("이영희").rankId(2).rankName("주임").departmentId(2).departmentName("마케팅부서")
                        .email("jane.smith@example.com").phoneNumber("555-5678").address("456 Elm St").gender("Female")
                        .joinDate(LocalDateTime.of(2022, 3, 12, 9, 0)).salary(60000).supervisorId(1).build(),
                Employee.builder().employeeId(3L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("박민수").rankId(3).rankName("책임").departmentId(3).departmentName("인사부서")
                        .email("mike.jones@example.com").phoneNumber("555-9012").address("789 Oak St").gender("Male")
                        .joinDate(LocalDateTime.of(2021, 6, 25, 9, 0)).salary(70000).supervisorId(2).build(),
                Employee.builder().employeeId(4L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("최수연").rankId(2).rankName("대리").departmentId(1).departmentName("기술부서")
                        .email("susan.brown@example.com").phoneNumber("555-3456").address("101 Pine St").gender("Female")
                        .joinDate(LocalDateTime.of(2023, 2, 10, 9, 0)).salary(55000).supervisorId(3).build(),
                Employee.builder().employeeId(5L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("정우성").rankId(1).rankName("과장").departmentId(2).departmentName("마케팅부서")
                        .email("tom.wilson@example.com").phoneNumber("555-7890").address("202 Cedar St").gender("Male")
                        .joinDate(LocalDateTime.of(2020, 11, 30, 9, 0)).salary(45000).supervisorId(4).build(),
                Employee.builder().employeeId(6L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("신혜진").rankId(2).rankName("차장").departmentId(3).departmentName("인사부서")
                        .email("lisa.white@example.com").phoneNumber("555-1235").address("303 Birch St").gender("Female")
                        .joinDate(LocalDateTime.of(2019, 5, 20, 9, 0)).salary(65000).supervisorId(5).build(),
                Employee.builder().employeeId(7L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("오준호").rankId(3).rankName("부장").departmentId(1).departmentName("기술부서")
                        .email("daniel.green@example.com").phoneNumber("555-5679").address("404 Spruce St").gender("Male")
                        .joinDate(LocalDateTime.of(2018, 4, 15, 9, 0)).salary(80000).supervisorId(6).build(),
                Employee.builder().employeeId(8L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("강하늘").rankId(3).rankName("이사").departmentId(2).departmentName("마케팅부서")
                        .email("nancy.black@example.com").phoneNumber("555-9013").address("505 Maple St").gender("Female")
                        .joinDate(LocalDateTime.of(2017, 7, 19, 9, 0)).salary(75000).supervisorId(7).build(),
                Employee.builder().employeeId(9L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("권민지").rankId(1).rankName("상무").departmentId(3).departmentName("인사부서")
                        .email("peter.gray@example.com").phoneNumber("555-3457").address("606 Redwood St").gender("Male")
                        .joinDate(LocalDateTime.of(2022, 8, 23, 9, 0)).salary(48000).supervisorId(8).build(),
                Employee.builder().employeeId(10L).employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO")
                        .employeeName("백승훈").rankId(2).rankName("전무").departmentId(1).departmentName("기술부서")
                        .email("emma.purple@example.com").phoneNumber("555-7891").address("707 Cherry St").gender("Female")
                        .joinDate(LocalDateTime.of(2023, 3, 18, 9, 0)).salary(62000).supervisorId(9).build()
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
                    .departmentId(e.getDepartmentId())
                    .department(e.getDepartmentName())
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
                        .department(e.getDepartmentName())
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

}