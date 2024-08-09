package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.MaterialItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialtemRepository extends JpaRepository<MaterialItem, Long> {
}
