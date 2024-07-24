package kr.co.groupworks.repository.workflow;

import kr.co.groupworks.entity.workflow.AttachmentFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFileEntity, Long> {
}
