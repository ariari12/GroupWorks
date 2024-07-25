package kr.co.groupworks.repository.workflow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkflowApproversRepositoryImpl extends QuerydslRepositorySupport implements WorkflowApproversRepository {
    private JPAQueryFactory queryFactory;

    @Autowired
    public WorkflowApproversRepositoryImpl(EntityManager em) {
        super(WorkFlowEntity.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<WorkFlowEntity> workflowListFindByApproverId(long approverEmployeeId, int approverType) {
        QWorkFlowEntity workFlowEntity = QWorkFlowEntity.workFlowEntity;
        QApproverEntity approverEntity = QApproverEntity.approverEntity;

        return queryFactory
                .selectFrom(workFlowEntity)
                .innerJoin(workFlowEntity.approvers, approverEntity).fetchJoin()
                .where(approverEntity.employeeId.eq(approverEmployeeId)
                        .and(approverEntity.approverType.eq(approverType)))
                .orderBy(workFlowEntity.draftDate.desc())
                .fetch();
    }

}
