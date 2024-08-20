package kr.co.groupworks.workflow.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.department.entity.QDepartment;
import kr.co.groupworks.workflow.dto.vo.WorkflowListVO;
import kr.co.groupworks.workflow.entity.QApproverEntity;
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
    public WorkflowApproversRepositoryImpl(EntityManager entityManager) {
        super(WorkFlowEntity.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
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
        QWorkFlowEntity workflow = QWorkFlowEntity.workFlowEntity;
        QDepartment department = QDepartment.department;

        List<Tuple> list = queryFactory
                .select(
                        Expressions.cases()
                                .when(workflow.status.in(0, 1, 2, 3)).then(1L)
                                .otherwise(0L).sum(),
                        Expressions.cases()
                                .when(workflow.status.eq(1)).then(1L)
                                .otherwise(0L).sum(),
                        Expressions.cases()
                                .when(workflow.status.eq(2)).then(1L)
                                .otherwise(0L).sum()
                ).from(department)
                .leftJoin(workflow).on(workflow.departmentId.eq(department.departmentId))
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
    public List<WorkFlowEntity> employeeWorkflowStat(Integer code, Long employeeId) {
        QWorkFlowEntity workFlowEntity = QWorkFlowEntity.workFlowEntity;
        QApproverEntity approverEntity = QApproverEntity.approverEntity;

        /* code: OpenAPI 명세에 사용자가 원하는 데이터 분류 Code */
        if (code == null || code < 1) {
            /* 사원에 모든 결재목록 */
            return queryFactory
                    .select(workFlowEntity).from(workFlowEntity)
                    .where(workFlowEntity.employeeId.eq(employeeId))
                    .fetch();
        }
        return switch (code) {
            /* 사원이 발송한 결재 중 승인된 결재목록 */
            case 1 -> queryFactory
                    .select(workFlowEntity).from(workFlowEntity)
                    .where(workFlowEntity.employeeId.eq(employeeId).and(workFlowEntity.status.eq(1)))
                    .fetch();
            /* 사원이 발송한 결재 중 반려된 결재목록 */
            case 2 -> queryFactory
                    .select(workFlowEntity).from(workFlowEntity)
                    .where(workFlowEntity.employeeId.eq(employeeId).and(workFlowEntity.status.eq(2)))
                    .fetch();
            /* 사원이 발송한 결재 중 진행 증인 결재목록 */
            case 3 -> queryFactory
                    .select(workFlowEntity).from(workFlowEntity)
                    .where(workFlowEntity.employeeId.eq(employeeId).and(
                            workFlowEntity.status.eq(0).or(workFlowEntity.status.eq(3))
                    ) ).fetch();
            /* 사원이 승인한 결재목록 */
            case 4 -> queryFactory
                    .select(workFlowEntity).from(workFlowEntity)
                    .where( workFlowEntity.approvers.contains( queryFactory
                                    .select(approverEntity).from(approverEntity)
                                    .where(approverEntity.employeeId.eq(employeeId).and(approverEntity.approval.eq(1)))
                            )
                    ).fetch();
            /* 사원이 반려한 결재목록 */
            case 5 -> queryFactory
                    .select(workFlowEntity).from(workFlowEntity)
                    .where( workFlowEntity.approvers.contains( queryFactory
                                    .select(approverEntity).from(approverEntity)
                                    .where(approverEntity.employeeId.eq(employeeId).and(approverEntity.approval.eq(2)))
                            )
                    ).fetch();
            default -> null;
        };
    }

    @Override
    public List<WorkflowListVO> recentWorkflowList(Long employeeId) {
        QWorkFlowEntity w = QWorkFlowEntity.workFlowEntity;
        QApproverEntity a = QApproverEntity.approverEntity;
        if (employeeId == null || employeeId < 1) return new ArrayList<>();

        return queryFactory
                .select(w).distinct().from(w)
                .innerJoin(a).on(a.workFlowId.eq(w.id))
                .where(w.employeeId.eq(employeeId).or(a.employeeId.eq(employeeId)))
                .orderBy(w.draftDate.desc()).limit(5)
                .fetch().stream().map(WorkflowListVO::new).toList();
    }
}
