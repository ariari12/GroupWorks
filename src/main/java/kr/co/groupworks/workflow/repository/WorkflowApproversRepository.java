package kr.co.groupworks.workflow.repository;

import kr.co.groupworks.workflow.entity.WorkFlowEntity;

import java.util.List;
import java.util.Map;

public interface WorkflowApproversRepository {
    List<WorkFlowEntity> workflowListFindByApproverId(long approverEmployeeId, int approverType);

    Map<String, List<Long>> workflowDepartmentStatistics();

    List<Long> workflowTypeByThisYear();

}
