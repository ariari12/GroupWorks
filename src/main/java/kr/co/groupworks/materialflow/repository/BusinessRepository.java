package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
}
