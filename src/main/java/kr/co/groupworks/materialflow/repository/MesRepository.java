package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Mes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {
    List<Mes> findByManufactureDateGreaterThanEqualAndManufactureDateLessThanEqual(LocalDateTime start, LocalDateTime end);
}
