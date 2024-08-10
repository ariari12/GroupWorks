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

    List<MaterialItemDTO> getItemList(long bomId);
}
