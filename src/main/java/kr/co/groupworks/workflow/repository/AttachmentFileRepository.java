package kr.co.groupworks.workflow.repository;

import kr.co.groupworks.workflow.entity.AttachmentFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFileEntity, Long> {
}
