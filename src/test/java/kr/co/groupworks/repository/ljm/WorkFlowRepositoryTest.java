package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.control.ljm.WorkFlowController;
import kr.co.groupworks.dto.ljm.dto.WorkFlowDTO;
import kr.co.groupworks.dto.ljm.vo.WorkflowListVO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.ljm.ApproverEntity;
import kr.co.groupworks.entity.ljm.WorkFlowEntity;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;

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


    @Test @DisplayName("Approve Sequence Test")
    public void approveSequenceTest() {
        log.info("Approve Sequence Test");
        int sequence = 2;
        WorkFlowDTO workFlowDTO = WorkFlowDTO.entityToDto(Objects.requireNonNull(workFlowRepository.findById(6L).orElse(null)));

        workFlowDTO.getApprovers().forEach(a -> {
            if(a.getApproverType() == 1 && a.getSequenceNum() >= sequence) {
                log.info("sequence:{}, approver sequence:{}", sequence, a.getSequenceNum());
                a.setApproverName("sequence Test");
            }
        });
        workFlowDTO.getApprovers().forEach(a -> log.info("approver:{}", a.getApproverName()));
    }
}