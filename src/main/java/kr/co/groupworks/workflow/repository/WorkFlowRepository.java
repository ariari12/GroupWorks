package kr.co.groupworks.workflow.repository;

import kr.co.groupworks.workflow.entity.WorkFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlowEntity, Long>, WorkflowApproversRepository {
    List<WorkFlowEntity> findByEmployeeId(long employeeId);

    List<WorkFlowEntity> findByDepartmentIdAndStatus(long departmentId, int status);

    @Query(value = "SELECT " +
            "    COALESCE(SUM(CASE WHEN w.status = 1 THEN 1 ELSE 0 END), 0) AS `승인`, " +
            "    COALESCE(SUM(CASE WHEN w.status = 2 THEN 1 ELSE 0 END), 0) AS `반려`, " +
            "    COALESCE(COUNT(w.status), 0) AS `발송`, " +
            "    month_table.month AS `월` " +
            "FROM " +
            "    (SELECT '01' AS month UNION ALL SELECT '02' UNION ALL SELECT '03' UNION ALL " +
            "     SELECT '04' UNION ALL SELECT '05' UNION ALL SELECT '06' UNION ALL " +
            "     SELECT '07' UNION ALL SELECT '08' UNION ALL SELECT '09' UNION ALL " +
            "     SELECT '10' UNION ALL SELECT '11' UNION ALL SELECT '12') AS month_table " +
            "LEFT JOIN workflow w " +
            "ON DATE_FORMAT(w.draft_date, '%m') = month_table.month " +
            "   AND DATE_FORMAT(w.draft_date, '%Y') = :year AND w.employee_id = :employeeId " +
            "GROUP BY month_table.month " +
            "ORDER BY month_table.month", nativeQuery = true)
    List<Map<String, Object>> monthlyWorkflowType(@Param("year") String year, @Param("employeeId") long employeeId);
}
