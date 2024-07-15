package kr.co.groupworks.entity.ljm;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkFlowEntity is a Querydsl query type for WorkFlowEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkFlowEntity extends EntityPathBase<WorkFlowEntity> {

    private static final long serialVersionUID = 1437203132L;

    public static final QWorkFlowEntity workFlowEntity = new QWorkFlowEntity("workFlowEntity");

    public final NumberPath<Integer> approvalCount = createNumber("approvalCount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> approvalDate = createDateTime("approvalDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> approverCount = createNumber("approverCount", Integer.class);

    public final ListPath<ApproverEntity, QApproverEntity> approvers = this.<ApproverEntity, QApproverEntity>createList("approvers", ApproverEntity.class, QApproverEntity.class, PathInits.DIRECT2);

    public final ListPath<AttachmentFileEntity, QAttachmentFileEntity> attachmentFiles = this.<AttachmentFileEntity, QAttachmentFileEntity>createList("attachmentFiles", AttachmentFileEntity.class, QAttachmentFileEntity.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final NumberPath<Long> cost = createNumber("cost", Long.class);

    public final StringPath department = createString("department");

    public final NumberPath<Long> departmentId = createNumber("departmentId", Long.class);

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> draftDate = createDateTime("draftDate", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> employeeId = createNumber("employeeId", Long.class);

    public final StringPath employeeName = createString("employeeName");

    public final StringPath employeeRank = createString("employeeRank");

    public final StringPath finalApprovalDepartment = createString("finalApprovalDepartment");

    public final StringPath finalApprovalName = createString("finalApprovalName");

    public final StringPath finalApprovalRank = createString("finalApprovalRank");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> workFlowType = createNumber("workFlowType", Integer.class);

    public QWorkFlowEntity(String variable) {
        super(WorkFlowEntity.class, forVariable(variable));
    }

    public QWorkFlowEntity(Path<? extends WorkFlowEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkFlowEntity(PathMetadata metadata) {
        super(WorkFlowEntity.class, metadata);
    }

}

