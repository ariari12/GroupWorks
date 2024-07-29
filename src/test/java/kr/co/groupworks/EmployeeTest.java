package kr.co.groupworks;

import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.VacationHistory;
import kr.co.groupworks.repository.cis.DepartmentRepository;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.kah.VacationHistoryRepository;
import kr.co.groupworks.workflow.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
public class EmployeeTest {
    /* 필요한 객체 추가 */
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VacationHistoryRepository vacationHistoryRepository;

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

        // 이미 존재하는 부서 데이터를 확인하고, 존재하지 않는 경우에만 저장
        // └> Id 자동생성으로 확인 불가(id 겹치면 데이터 중복 생성됨)
//        departments.forEach(department -> {
//            Optional<Department> existingDepartment = departmentRepository.findById(department.getDepartmentId());
//            if (existingDepartment.isEmpty()) {
//                departmentRepository.save(department);
//            }
//        });

        // 부서 데이터 생성 및 사원 데이터 생성 시 사용
        List<Department> finalDepartments = departmentRepository.saveAll(departments);
        // 샘플 사원 데이터 생성
        List<Employee> employees = IntStream.rangeClosed(1, 100).mapToObj(i -> Employee.builder()
                .employeeId((long) i)
                .employeePW("$2a$10$Vg4CIc8WunwnKoV2.j9J.uPep8BgLAzb2VelL89I.hGiLBDNoybpO") // employeePW: 사원 비밀번호 1111
                .employeeName("사원" + i) // employeeName: 사원 이름 (사원1, 사원2, ..., 사원100)
                .rankId((i % 5) + 1)    // rankId: 사원 직급 ID (1부터 5까지 반복)
                .rankName("직급" + (i % 5 + 1)) // rankName: 사원 직급 이름 (직급1, 직급2, ..., 직급5 반복)
                .department(finalDepartments.get(i % finalDepartments.size())) // department: 부서 (부서 목록에서 순환 선택)
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
        // 저장된 데이터 출력 (확인 용)
        employees.forEach(employee ->
                System.out.println(employee.getEmployeeId() + ": " +
                        employee.getEmployeeName() + ", " +
                        employee.getEmail() + ", " +
                        employee.getPhoneNumber() + ", " +
                        employee.getDepartment().getDepartmentName())
        );

        // VacationHistory 데이터 생성 및 저장
        List<VacationHistory> vacationHistories = employees.stream()
                .map(VacationHistory::createFromEmployee)
                .toList();
        vacationHistoryRepository.saveAll(vacationHistories);
    }


}
