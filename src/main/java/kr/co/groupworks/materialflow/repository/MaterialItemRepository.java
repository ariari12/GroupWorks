package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.MaterialItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialItemRepository extends JpaRepository<MaterialItem, Long> {
    List<MaterialItem> findByBomId(Long bomId);

    List<MaterialItem> findByItemCodeIn(List<String> itemCodes);
}
