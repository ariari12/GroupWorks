package kr.co.groupworks.repository.workflow;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.entity.cis.QDepartment;
import kr.co.groupworks.entity.workflow.QApproverEntity;
import kr.co.groupworks.entity.workflow.QWorkFlowEntity;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WorkflowApproversRepositoryImpl extends QuerydslRepositorySupport implements WorkflowApproversRepository {
    private static final Logger log = LoggerFactory.getLogger(WorkflowApproversRepositoryImpl.class);
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

    @Override
    public Map<String, List<Long>> workflowDepartmentStatistics() {
        QWorkFlowEntity workFlow = QWorkFlowEntity.workFlowEntity;
        QDepartment department = QDepartment.department;

        List<Tuple> list = queryFactory
                .select(
                        workFlow.status
                                .when(1).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlow.status
                                .when(2).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlow.status
                                .when(0).then(1L)
                                .when(3).then(1L)
                                .otherwise(0L)
                                .sum().as("test03")
                )
                .from(department)
                .leftJoin(workFlow).on(workFlow.departmentId.eq(department.departmentId))
                .groupBy(department.departmentId)
                .fetch();

        String[] keys = {"request", "approve", "reject"};
        Map<String, List<Long>> map = Map.of( keys[0], new ArrayList<>(), keys[1], new ArrayList<>(), keys[2], new ArrayList<>() );
        list.forEach(t -> {
            for (int i = 0; i < 3; i++) {
                map.get(keys[i]).add(t.get(i, Long.class));
            }
        });

        return map;
    }

    @Override
    public List<Long> workflowTypeByThisYear() {
        QWorkFlowEntity workFlowEntity = QWorkFlowEntity.workFlowEntity;

        List<Tuple> tuple = queryFactory
                .select(
                        workFlowEntity.workFlowType
                                .when(1).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlowEntity.workFlowType
                                .when(2).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlowEntity.workFlowType
                                .when(3).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlowEntity.workFlowType
                                .when(4).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlowEntity.workFlowType
                                .when(5).then(1L)
                                .otherwise(0L)
                                .sum(),
                        workFlowEntity.workFlowType
                                .when(6).then(1L)
                                .otherwise(0L)
                                .sum()
                )
                .from(workFlowEntity)
                .where(workFlowEntity.draftDate.year().eq(LocalDateTime.now().getYear()))
                .orderBy(workFlowEntity.workFlowType.asc())
                .fetch();

        List<Long> typeList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            typeList.add(tuple.get(0).get(i, Long.class));
        }

        return typeList;
    }

}
