package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.BusinessManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessManagerRepository extends JpaRepository<BusinessManager, Long> {
}
