package kr.co.groupworks.workflow.repository;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.workflow.entity.ApproverEntity;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
@Transactional
public class WorkflowInsertData {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkFlowRepository workFlowRepository;

    @Autowired
    ApproversRepository approversRepository;

    final Map<Integer, String> workFlowTitleDescription = Map.of(
            1, " 업무 기안 결재",
            2, " 재무 결산입니다.",
            3, " 예산 기안 요청",
            4, " 비품 구매사항 비용 청구입니다.",
            5, " 보고서 관련 결재 요청입니다.",
            6, " 문제 발생 관련하여 결재 요청바랍니다."
    );

    int sequenceNum = 0;

    @Test
    void test() {
        List<Employee> empList = employeeRepository.findAll();
        // 부서별로 직원 리스트 그룹화 (부서 ID -> 직원 리스트)
        Map<Long, List<Employee>> departmentMap = empList.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getDepartmentId()));

        // 기안자 선택 (사원 번호가 10, 20, 30, ... 90, 100 인 직급: 사원)
        List<Employee> drafterList = empList.stream()
                .filter(e -> e.getEmployeeId() % 10 == 0)
                .toList();

        departmentMap.forEach((deptId, employees) -> {
            var ref = new Object() { int cnt = 0; };
            String dName = employees.get(0).getDepartment().getDepartmentName();

            // 결재자 (직급이 과장이상)
            List<Employee> approvers = employees.stream().filter(e -> e.getRankId() >= 3)
                    .limit(3).sorted(Comparator.comparingInt(Employee::getRankId)).toList();
            // 협조자 (타 부서)
            List<Employee> cooperators = empList.stream()
                    .filter(e -> !e.getDepartment().getDepartmentId().equals(deptId) && e.getRankId() >= 3)
                    .limit(3).toList();
            // 참조자 선정
            List<Employee> referents = empList.stream()
                    .filter(e -> !drafterList.contains(e) && !approvers.contains(e) && !cooperators.contains(e))
                    .limit(3).toList();
            // 기안자 부서 당 1명
            Employee drafter = drafterList.stream().filter(e -> e.getDepartment().getDepartmentId().equals(deptId))
                    .findFirst().get();

            employees.forEach(e -> {
                List<Integer> approval = randomApporvalList(approvers.size());
                log.info(approval.toString());
                LocalDateTime draftTime = generateRandomDateTime();

                if(ref.cnt > 5) return;
                Employee fa = approvers.get(approvers.size() -1);
                Map<String, Object> map = getRandSet(deptId, dName);

                int status = 0, method = 1;
                LocalDateTime approvalDate = null;
                long wId = workFlowRepository.save(WorkFlowEntity.builder().build()).getId();

                List<ApproverEntity> approverList = new ArrayList<>();
                sequenceNum = 1;
                int nextApproval = approval.get(0);
                for (int i = 0; i < approvers.size(); i++) {
                    if(nextApproval == 0) {
                        approverList.add(createApproverEntity(wId, approvers.get(i), 1, nextApproval, method, null, null));
                    } else if(nextApproval == 2 || nextApproval == 3) {
                        String cmt = nextApproval == 2? "반려합니디." : "전결합니다.";
                        method = nextApproval == 3? 4 : 5;
                        approvalDate = draftTime.plusDays(3);
                        nextApproval = nextApproval == 3 ? 1 : nextApproval;
                        approverList.add(createApproverEntity(wId, approvers.get(i), 1, nextApproval, method, cmt, approvalDate));
                        status = nextApproval == 2? 2 : 1;
                        nextApproval = 0;
                    } else {
                        approvalDate = approvalDate != null ? approvalDate.plusDays(1) : draftTime.plusDays(1);
                        approverList.add(createApproverEntity(wId, approvers.get(i), 1, nextApproval, method, "결재 승인합니다.", draftTime.plusDays(1)));
                        status = 3;
                        nextApproval = i < approvers.size() -1? approval.get(i +1): 0;
                    }
                }
                sequenceNum = 1;
                cooperators.forEach(cooperator -> {
                    approverList.add(createApproverEntity(wId, cooperator, 2, 1, 0, "협의 합니다.", draftTime.plusDays(1)));
                });
                sequenceNum = 1;
                referents.forEach(referent -> {
                    approverList.add(createApproverEntity(wId, referent, 3, 0, 0, null, null));
                });

                // WorkFlowEntity 생성 및 리스트에 추가
                workFlowRepository.save(WorkFlowEntity.builder()
                        .id(wId)
                        /* 기안자 */
                        .employeeId(drafter.getEmployeeId())
                        .employeeName(drafter.getEmployeeName())
                        .phone(drafter.getPhoneNumber())
                        .email(drafter.getEmail())
                        .employeeRank(drafter.getRankName())
                        .draftDate(draftTime)

                        /* 부서정보 */
                        .departmentId(deptId)
                        .department(dName)

                        /* 결재 정보 */
                        .code(map.get("code").toString())
                        .workFlowType((Integer) map.get("type"))
                        .title(map.get("content").toString())
                        .description(map.get("content").toString())
                        .cost((Long) map.get("cost"))

                        .finalEmployeeId(fa.getEmployeeId())
                        .finalApprovalRank(fa.getRankName())
                        .finalApprovalDepartment(fa.getDepartment().getDepartmentName())
                        .finalApprovalName(fa.getEmployeeName())
                        .approvalDate(approvalDate)

                        /* 결재 상황 */
                        .approvers(approverList)
                        .approverCount(approvers.size())
                        .approvalCount(method == 4? approval.size() : (int) approverList.stream()
                                .filter(a -> a.getApproverType() == 1 && a.getApproval() > 0).count())
                        .status(status) // 초기값
                        .build()
                );
                ref.cnt++;
            });
        });
    }

    ApproverEntity createApproverEntity(Long wId, Employee employee, int approverType, Integer approval, int method, String cmt, LocalDateTime approvalDate) {
        return ApproverEntity.builder()
                .workFlowId(wId)
                .sequenceNum(++sequenceNum)
                .approverType(approverType)

                .employeeId(employee.getEmployeeId())
                .approverEmail(employee.getEmail())
                .approverPhone(employee.getPhoneNumber())
                .approverName(employee.getEmployeeName())
                .approverRank(employee.getRankName())
                .department(employee.getDepartment().getDepartmentName())

                .approvalMethod(method)
                .approvalDate(approvalDate)
                .approval(approval) // 초기값
                .comment(cmt)
                .build();
    }

    Map<String, Object> getRandSet(Long dId, String dName) {
        String randStr = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 6);
        int type = (int) (Math.random() * 6) +1;
        String content = dName + " " + randStr + workFlowTitleDescription.get(type);
        // 예산, 재무, 구매 결재 이거나 영업, 마케팅, 재무부서 인 경우 비용 추가
        Long cost = type == 2 || type == 3 || type == 4 || dId == 2 || dId == 3 || dId == 5 ?
                (long) (Math.random() * 3) * 1200000 : 0L;

        return Map.of("code", UUID.randomUUID().toString(), "type", type, "content", content, "cost", cost);
    }

    List<Integer> randomApporvalList(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // 0, 1, 2, 3 중 하나의 랜덤 값을 생성
            int randomValue = random.nextInt(4);
            list.add(randomValue);
        }
        return list;
    }

    private static final LocalDate START_DATE = LocalDate.of(2024, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2024, 8, 20);
    LocalDateTime generateRandomDateTime() {
        Random random = new Random();
        long startEpochDay = START_DATE.toEpochDay();
        long endEpochDay = END_DATE.toEpochDay();
        long randomDay = startEpochDay + random.nextInt((int) (endEpochDay - startEpochDay + 1));

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return LocalDateTime.of(randomDate, getRandomTimeOfDay());
    }
    LocalTime getRandomTimeOfDay() {
        Random random = new Random();
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return LocalTime.of(hour, minute, second);
    }
}