package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    List<Business> findByIdGreaterThan(Long id);
}
