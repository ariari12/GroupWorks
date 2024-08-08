package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.materialflow.dto.BomDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.dto.MesDTO;
import kr.co.groupworks.materialflow.dto.OrderDTO;
import kr.co.groupworks.materialflow.entity.Business;

import java.util.List;

public interface MaterialOpenApiService {
    Object getBusiness(Long businessId);

    boolean setBusinessList(List<Business> businessList);

    ManagerDTO getManager(Long managerId);
    List<ManagerDTO> getAllManager();
    List<ManagerDTO> getManagersByBusiness(Long businessId);

    void setManagers(List<ManagerDTO> managerList);

    List<OrderDTO> getOrderList(String orderCode, String itemCode, String itemName);

    List<MesDTO> setMesList(List<MesDTO> mesList);

    OrderDTO getOrder(Long orderId);

    List<BomDTO> getBomList();
}
