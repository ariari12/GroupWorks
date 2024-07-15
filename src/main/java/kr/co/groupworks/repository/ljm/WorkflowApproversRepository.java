package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.entity.ljm.WorkFlowEntity;

import java.util.List;

public interface WorkflowApproversRepository {
    List<WorkFlowEntity> findByApproverEmployeeId(long approverId);
}
