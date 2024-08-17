package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.dto.MesListVO;
import kr.co.groupworks.materialflow.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderQueryDsl {
    List<Order> findByOrderCodeAndItemCodeAndItemName(String orderCode, String itemCode, String itemName);

    List<Order> findByBomList();

    boolean orderDeleteCheck(Long orderId);

    boolean orderCompleteCheck(Long bomId, int isStat);

    Order findByBomId(Long bomId);

    Map<String, Long> findIdMapByBomItemCode(String bomItemCode);

    List<MesListVO> findAllMesAndOrderCode();

    Long calculat(LocalDate start, LocalDate end);
}
