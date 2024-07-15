package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.entity.ljm.WorkFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlowRepository extends JpaRepository<WorkFlowEntity, Long>, WorkflowApproversRepository {
}
