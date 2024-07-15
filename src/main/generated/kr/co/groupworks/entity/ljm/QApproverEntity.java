package kr.co.groupworks.entity.ljm;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QApproverEntity is a Querydsl query type for ApproverEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApproverEntity extends EntityPathBase<ApproverEntity> {

    private static final long serialVersionUID = -1323314910L;

    public static final QApproverEntity approverEntity = new QApproverEntity("approverEntity");

    public final NumberPath<Integer> approval = createNumber("approval", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> approvalDate = createDateTime("approvalDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> approvalMethod = createNumber("approvalMethod", Integer.class);

    public final StringPath approverEmail = createString("approverEmail");

    public final StringPath approverName = createString("approverName");

    public final StringPath approverPhone = createString("approverPhone");

    public final StringPath approverRank = createString("approverRank");

    public final NumberPath<Integer> approverType = createNumber("approverType", Integer.class);

    public final StringPath comment = createString("comment");

    public final StringPath department = createString("department");

    public final NumberPath<Long> employeeId = createNumber("employeeId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> sequenceNum = createNumber("sequenceNum", Integer.class);

    public final NumberPath<Long> workFlowId = createNumber("workFlowId", Long.class);

    public QApproverEntity(String variable) {
        super(ApproverEntity.class, forVariable(variable));
    }

    public QApproverEntity(Path<? extends ApproverEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApproverEntity(PathMetadata metadata) {
        super(ApproverEntity.class, metadata);
    }

}

