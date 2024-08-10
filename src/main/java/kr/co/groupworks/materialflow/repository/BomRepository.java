package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository extends JpaRepository<Bom, Long> {
}
