package kr.co.groupworks.service.workFlow;

import jakarta.transaction.Transactional;
import kr.co.group_workers.entity.workFlow.WorkFlow;
import kr.co.group_workers.entity.workFlow.WorkFlow2;
import kr.co.group_workers.repository.workFlow.WorkFlow1Repository;
import kr.co.group_workers.repository.workFlow.WorkFlow2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("WorkFlow2Service")
@RequiredArgsConstructor
@Transactional
public class WorkFlow2ServiceImpl implements WorkFlowService {
    private final WorkFlow2Repository workFlow2Repository;
    private final WorkFlow1Repository workFlow1Repository;

    public WorkFlow2 loadWorkFlow2(int no) {
        return workFlow2Repository.findById(no).orElse(null);
    }

    @Override
    public WorkFlow loadWorkFlow(int no) {
        return workFlow1Repository.findById(no).orElse(null);
    }

    @Override
    public void addWorkFlow(WorkFlow workFlow) {
        workFlow1Repository.save(workFlow);
    }
}
