package kr.co.groupworks.materialflow.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.materialflow.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class OrderQueryDslImpl extends QuerydslRepositorySupport implements OrderQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public OrderQueryDslImpl(EntityManager entityManager) {
        super(Order.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }



}
