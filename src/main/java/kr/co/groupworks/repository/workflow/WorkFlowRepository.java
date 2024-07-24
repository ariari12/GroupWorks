package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlowEntity, Long>, WorkflowApproversRepository {
    List<WorkFlowEntity> findByEmployeeId(long employeeId);
}
