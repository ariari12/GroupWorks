package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Mes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {
}
