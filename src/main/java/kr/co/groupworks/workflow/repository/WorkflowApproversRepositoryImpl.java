package kr.co.groupworks.workflow.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.department.entity.QDepartment;
import kr.co.groupworks.workflow.entity.QApproverEntity;
import kr.co.groupworks.workflow.entity.QAttachmentFileEntity;
import kr.co.groupworks.workflow.entity.QWorkFlowEntity;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class WorkflowApproversRepositoryImpl extends QuerydslRepositorySupport implements WorkflowApproversRepository {
    private final JPAQueryFactory queryFactory;

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

    @Override
    public List<WorkFlowEntity> employeeWorkflowStat(Integer c, Long e) {
        QWorkFlowEntity w = QWorkFlowEntity.workFlowEntity;
        QApproverEntity a = QApproverEntity.approverEntity;
        QAttachmentFileEntity fs = QAttachmentFileEntity.attachmentFileEntity;

        if (c == null || c < 1) {
            /* 사원에 모든 결재목록 */
            return queryFactory
                    .select(w).from(w)
                    .where(w.employeeId.eq(e))
                    .fetch();
        }
        return switch (c) {
            /* 사원이 발송한 결재 중 승인된 결재목록 */
            case 1 -> queryFactory
                    .select(w).from(w)
                    .where(w.employeeId.eq(e).and(w.status.eq(1)))
                    .fetch();
            /* 사원이 발송한 결재 중 반려된 결재목록 */
            case 2 -> queryFactory
                    .select(w).from(w)
                    .where(w.employeeId.eq(e).and(w.status.eq(2)))
                    .fetch();
            /* 사원이 발송한 결재 중 진행 증인 결재목록 */
            case 3 -> queryFactory
                    .select(w).from(w)
                    .where(w.employeeId.eq(e).and(
                            w.status.eq(0).or(w.status.eq(3))
                    ) ).fetch();
            /* 사원이 승인한 결재목록 */
            case 4 -> queryFactory
                    .select(w).from(w)
                    .where( w.approvers.contains( queryFactory
                                    .select(a).from(a)
                                    .where(a.employeeId.eq(e).and(a.approval.eq(1)))
                            )
                    ).fetch();
            /* 사원이 반려한 결재목록 */
            case 5 -> queryFactory
                    .select(w).from(w)
                    .where( w.approvers.contains( queryFactory
                                    .select(a).from(a)
                                    .where(a.employeeId.eq(e).and(a.approval.eq(2)))
                            )
                    ).fetch();
            default -> null;
        };
    }
}
