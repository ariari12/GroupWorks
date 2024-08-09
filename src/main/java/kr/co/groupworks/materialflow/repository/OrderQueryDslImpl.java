package kr.co.groupworks.materialflow.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.groupworks.materialflow.entity.Order;
import kr.co.groupworks.materialflow.entity.QBom;
import kr.co.groupworks.materialflow.entity.QOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderQueryDslImpl extends QuerydslRepositorySupport implements OrderQueryDsl {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public OrderQueryDslImpl(EntityManager entityManager) {
        super(Order.class);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Order> findByOrderCodeAndItemCodeAndItemName(String orderCode, String itemCode, String itemName) {
        if(itemName == null || itemName.isEmpty()) return findByItemName(orderCode, itemCode, itemName);
        return findByOrderCodeOrItemCode(orderCode, itemCode);
    }

    @Override
    public List<Order> findByBomList() {
        QOrder o = QOrder.order;
        QBom b = QBom.bom;

        return queryFactory
                .select(o)
                .from(o).leftJoin(o.boms, b)
                .fetchJoin().fetch();
    }

    private List<Order> findByItemName(String orderCode, String itemCode, String itemName) {
        QOrder o = QOrder.order;
        QBom b = QBom.bom;

        if (orderCode == null || orderCode.isEmpty()) {
            return queryFactory
                    .select(o)
                    .join(b).on(o.boms.contains(b))
                    .where(b.itemName.contains(itemName).and(b.itemCode.contains(itemCode)))
                    .fetchJoin().fetch();
        }
        else if (itemCode == null || itemCode.isEmpty()) {
            return queryFactory
                    .select(o)
                    .join(b).on(o.boms.contains(b))
                    .where(b.itemName.contains(itemName).and(o.orderCode.contains(orderCode)))
                    .fetchJoin().fetch();
        }
        else {
            return queryFactory
                    .select(o)
                    .join(b).on(o.boms.contains(b))
                    .where(b.itemName.contains(itemName).and(b.itemCode.contains(itemCode).and(o.orderCode.contains(orderCode))))
                    .fetchJoin().fetch();
        }
    }

    private List<Order> findByOrderCodeOrItemCode(String orderCode, String itemCode) {
        QOrder o = QOrder.order;
        QBom b = QBom.bom;

        if (orderCode == null || orderCode.isEmpty()) {
            return queryFactory
                    .select(o)
                    .join(b).on(o.boms.contains(b))
                    .where(b.itemCode.contains(itemCode))
                    .fetchJoin().fetch();
        }
        else if (itemCode == null || itemCode.isEmpty()) {
            return queryFactory
                    .select(o)
                    .join(b).on(o.boms.contains(b))
                    .where(o.orderCode.contains(orderCode))
                    .fetchJoin().fetch();
        }
        else {
            return queryFactory
                    .select(o)
                    .join(b).on(o.boms.contains(b))
                    .where(b.itemCode.contains(itemCode).and(o.orderCode.contains(orderCode)))
                    .fetchJoin().fetch();
        }
    }
}
