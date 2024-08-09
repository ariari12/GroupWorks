package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryDsl {
}
