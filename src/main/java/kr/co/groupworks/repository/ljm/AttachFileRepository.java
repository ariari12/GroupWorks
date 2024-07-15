package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.entity.ljm.AttachmentFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachmentFileEntity, Long> {
}
