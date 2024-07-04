package kr.co.groupworks.service.workFlow;

import kr.co.group_workers.entity.workFlow.WorkFlow;

public interface WorkFlowService {
    WorkFlow loadWorkFlow(int no);

    void addWorkFlow(WorkFlow workFlow);
}
