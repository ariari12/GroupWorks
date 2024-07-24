package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
