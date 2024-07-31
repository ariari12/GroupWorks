package kr.co.groupworks;

import kr.co.groupworks.calendar.entity.VacationHistory;
import kr.co.groupworks.calendar.repository.VacationHistoryRepository;

import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.department.repository.DepartmentRepository;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Transactional
@SpringBootTest
public class EmployeeTest {
    /* 필요한 객체 추가 */
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VacationHistoryRepository vacationHistoryRepository;

    @Test @Rollback(false)
    @DisplayName("Employee Insert")
    public void insertDepartment() {
        // 샘플 부서 데이터
        List<Department> departments = List.of(
                new Department(null, "기술부서", "010-1234-5678", "A동"),
                new Department(null, "마케팅부서", "010-1234-5679", "B동"),
                new Department(null, "영업부서", "010-1234-5680", "C동"),
                new Department(null, "인사부서", "010-1234-5681", "D동"),
                new Department(null, "재무부서", "010-1234-5682", "E동"),
                new Department(null, "IT 지원부서", "010-1234-5683", "F동"),
                new Department(null, "제품 관리부서", "010-1234-5684", "G동"),
                new Department(null, "고객 서비스부서", "010-1234-5685", "H동"),
                new Department(null, "법무부서", "010-1234-5686", "I동"),
                new Department(null, "연구개발부서", "010-1234-5687", "J동")
        );
        // 부서 데이터 생성 및 저장
        departmentRepository.saveAll(departments);

        // 영속성 컨텍스트에서 부서 데이터를 재조회
        List<Department> finalDepartments = departmentRepository.findAll();

        // 샘플 사원 데이터 생성
        List<Employee> employeeList = IntStream.rangeClosed(1, 100).mapToObj(i -> Employee.builder()
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
        ).collect(Collectors.toList());
        // 사원 데이터 저장
        employeeRepository.saveAll(employeeList);

        // 저장된 데이터 출력 (확인 용)
        employeeRepository.findAll().forEach(employee ->
                System.out.println(employee.getEmployeeId() + ": " +
                        employee.getEmployeeName() + ", " +
                        employee.getEmail() + ", " +
                        employee.getPhoneNumber() + ", " +
                        employee.getDepartment().getDepartmentName())
        );
        // VacationHistory 데이터 생성 및 저장
        List<VacationHistory> vacationHistories = employeeList.stream()
                .map(VacationHistory::createFromEmployee)
                .toList();
        vacationHistoryRepository.saveAll(vacationHistories);
    }
}
