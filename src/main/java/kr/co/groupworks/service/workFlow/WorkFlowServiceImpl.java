package kr.co.groupworks.service.workFlow;

import jakarta.transaction.Transactional;

import kr.co.groupworks.entity.workFlow.WorkFlow;
import kr.co.groupworks.repository.workFlow.WorkFlow1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkFlowServiceImpl implements WorkFlowService {
    private final WorkFlow1Repository workFlow1Repository;

    @Override
    public WorkFlow loadWorkFlow(int no) {
        return workFlow1Repository.findById(no).orElse(null);
    }

    @Override
    public void addWorkFlow(WorkFlow workFlow) {
        workFlow1Repository.save(workFlow);
    }
}
