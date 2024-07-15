package kr.co.groupworks.entity.ljm;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttachmentFileEntity is a Querydsl query type for AttachmentFileEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttachmentFileEntity extends EntityPathBase<AttachmentFileEntity> {

    private static final long serialVersionUID = 1820796060L;

    public static final QAttachmentFileEntity attachmentFileEntity = new QAttachmentFileEntity("attachmentFileEntity");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath savePath = createString("savePath");

    public final NumberPath<Long> workFlowId = createNumber("workFlowId", Long.class);

    public QAttachmentFileEntity(String variable) {
        super(AttachmentFileEntity.class, forVariable(variable));
    }

    public QAttachmentFileEntity(Path<? extends AttachmentFileEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttachmentFileEntity(PathMetadata metadata) {
        super(AttachmentFileEntity.class, metadata);
    }

}

