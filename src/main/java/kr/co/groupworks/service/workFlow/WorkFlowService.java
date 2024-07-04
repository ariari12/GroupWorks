package kr.co.groupworks.service.workFlow;


import kr.co.groupworks.entity.workFlow.WorkFlow;

public interface WorkFlowService {
    WorkFlow loadWorkFlow(int no);

    void addWorkFlow(WorkFlow workFlow);
}
