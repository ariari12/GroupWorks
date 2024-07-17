package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.entity.ljm.AttachmentFileOnlyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileOnlyRepository extends JpaRepository<AttachmentFileOnlyEntity, Long> {
}
