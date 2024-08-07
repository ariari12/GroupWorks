package kr.co.groupworks.materialflow.service;


import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.EmployeeDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.dto.OrderDTO;

import java.util.List;
import java.util.Map;

public interface MaterialService {
    List<EmployeeDTO> getAllEmployee();

    ManagerDTO setManager(ManagerDTO manager, Long businessId);

    Object setBusiness(BusinessDTO business);

    Object setOrder(OrderDTO orderDTO);

    Map<String, List<OrderDTO>> getOrders();
}
