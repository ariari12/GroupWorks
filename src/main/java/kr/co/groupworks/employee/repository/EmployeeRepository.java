package kr.co.groupworks.employee.repository;

import kr.co.groupworks.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name);

    public Employee findByEmail(String email);

//    메일 작성 시 실시간으로 해당되는 메일 목록 출력
    @Query("SELECT e.email FROM Employee e WHERE e.email LIKE CONCAT('%', :query, '%')")
    List<String> findEmailsByQuery(@Param("query") String query);

//    부서Id에 해당하는 사원 목록 가져오기
    List<Employee> findEmployeeByDepartment_DepartmentId(Long departmentId);

//    해당 부서에 존재하는 직급목록 가져오기
    @Query("SELECT distinct e.rankId  FROM Employee e WHERE e.department.departmentId = :departmentId ")
    List<Integer> findRankIdByDepartmentId(@Param("departmentId") Long departmentId);
}
