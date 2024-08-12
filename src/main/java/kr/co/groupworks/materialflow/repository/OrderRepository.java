package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Order;
import kr.co.groupworks.materialflow.entity.OrderClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryDsl {
    List<Order> findByClassification(OrderClassification classification);

    List<Order> findByManagerId(Long mId);
}
