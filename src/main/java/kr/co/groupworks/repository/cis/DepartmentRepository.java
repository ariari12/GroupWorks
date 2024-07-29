package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "select d.departmentName from Department d order by 1 asc ")
    List<Object> getDepartments();
}
