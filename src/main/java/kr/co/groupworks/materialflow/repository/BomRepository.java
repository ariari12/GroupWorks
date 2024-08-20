package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepository extends JpaRepository<Bom, Long> {

    @Query("select b.status from Bom b where b.id = :bomId")
    Boolean findStatusById(@Param("bomId") Long bomId);
}
