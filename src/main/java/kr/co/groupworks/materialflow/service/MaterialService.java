package kr.co.groupworks.materialflow.service;


import kr.co.groupworks.materialflow.dto.*;

import java.util.List;
import java.util.Map;

public interface MaterialService {
    List<EmployeeDTO> getAllEmployee();

    ManagerDTO setManager(ManagerDTO manager, Long businessId);

    Object setBusiness(BusinessDTO business);

    Object setOrder(OrderDTO orderDTO);

    Map<String, List<OrderDTO>> getOrders();

    List<OrderBomListVO> getBomList();

    List<MaterialItemVO> getItemList(long bomId);

    Map<String, Object> deleteOrder(Long orderId);

    Map<String, Object> deleteManager(Long managerId);

    Map<String, Object> deleteBusiness(Long businessId);

    Map<String, Object> updateItems(MaterialStatusUpdateDTO itemDTO);

    Map<String, Object> updateItem(Long itemId, int statusCode);

    Map<String, Object> orderCompleteCheck(Long bomId, int stat);

    Map<String, Object> getBomSMS(Long bomId);
}
