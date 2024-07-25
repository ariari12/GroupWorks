package kr.co.groupworks.repository.workflow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.control.workflow.WorkFlowController;
import kr.co.groupworks.entity.cis.QDepartment;
import kr.co.groupworks.entity.workflow.QApproverEntity;
import kr.co.groupworks.entity.workflow.QWorkFlowEntity;
import kr.co.groupworks.entity.workflow.WorkFlowEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

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
    public Map<String, List<Long>> workflowApprovalSatisticsByDepartment() {
        QWorkFlowEntity workFlowEntity = QWorkFlowEntity.workFlowEntity;
        QDepartment department = QDepartment.department;

        List<Long> results01 = queryFactory
                .select(workFlowEntity.count().coalesce(0L))
                .from(department)
                .leftJoin(workFlowEntity).fetchJoin()
                .on(workFlowEntity.departmentId.eq(department.departmentId))
                .where(workFlowEntity.status.eq(0).or(workFlowEntity.status.eq(3).or(workFlowEntity.status.isNull())))
                .groupBy(department.departmentId, department.departmentName, workFlowEntity.status)
                .orderBy(department.departmentId.asc())
                .fetch();

        List<Long> results02 = queryFactory
                .select(workFlowEntity.count().coalesce(0L))
                .from(department)
                .leftJoin(workFlowEntity).fetchJoin()
                .on(workFlowEntity.departmentId.eq(department.departmentId))
                .where(workFlowEntity.status.eq(1).or(workFlowEntity.status.isNull()))
                .groupBy(department.departmentId, department.departmentName, workFlowEntity.status)
                .orderBy(department.departmentId.asc())
                .fetch();

        List<Long> results03 = queryFactory
                .select(workFlowEntity.count().coalesce(0L))
                .from(department)
                .leftJoin(workFlowEntity).fetchJoin()
                .on(workFlowEntity.departmentId.eq(department.departmentId))
                .where(workFlowEntity.status.eq(2).or(workFlowEntity.status.isNull()))
                .groupBy(department.departmentId, department.departmentName, workFlowEntity.status)
                .orderBy(department.departmentId.asc())
                .fetch();

        Map<String, List<Long>> map = Map.of(
                WorkFlowController.AttributeName.REQUEST.getStatus(), results01,
                WorkFlowController.AttributeName.APPROVAl.getStatus(), results02,
                WorkFlowController.AttributeName.REJECTION.getStatus(), results03
        );

        log.info("map: {}", map);

        return map;
    }

}
