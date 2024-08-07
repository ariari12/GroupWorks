package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Order;

import java.util.List;

public interface OrderQueryDsl {
    List<Order> findByOrderCodeAndItemCodeAndItemName(String orderCode, String itemCode, String itemName);
}
