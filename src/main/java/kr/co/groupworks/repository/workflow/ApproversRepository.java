package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.entity.workflow.ApproverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApproversRepository extends JpaRepository<ApproverEntity, Long> {
    List<ApproverEntity> findByEmployeeIdAndApprovalMethodAndApproval(long employeeId, int approvalMethod, int approval);

    List<ApproverEntity> findByEmployeeId(Long employeeId);
}
