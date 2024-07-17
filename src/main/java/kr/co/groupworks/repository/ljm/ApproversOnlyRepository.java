package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.entity.ljm.ApproverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproversOnlyRepository extends JpaRepository<ApproverEntity, Long> {
}
