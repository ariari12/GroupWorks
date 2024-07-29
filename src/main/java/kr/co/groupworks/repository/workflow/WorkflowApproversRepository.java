package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.entity.workflow.WorkFlowEntity;

import java.util.List;
import java.util.Map;

public interface WorkflowApproversRepository {
    List<WorkFlowEntity> workflowListFindByApproverId(long approverEmployeeId, int approverType);

    Map<String, List<Long>> workflowDepartmentStatistics();

    List<Long> workflowTypeByThisYear();

}
