package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.entity.workflow.WorkFlowEntity;

import java.util.List;

public interface WorkflowApproversRepository {
    List<WorkFlowEntity> workflowListFindByApproverId(long approverEmployeeId, int approverType);
}
