package kr.co.groupworks.department.repository;

import kr.co.groupworks.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "select d.departmentName from Department d order by 1 asc ")
    List<Object> getDepartments();
}
