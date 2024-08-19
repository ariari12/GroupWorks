package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.materialflow.dto.*;
import kr.co.groupworks.materialflow.entity.Business;

import java.util.List;
import java.util.Map;

public interface MaterialOpenApiService {
    boolean setBusinessList(List<Business> businessList);

    Object getBusiness(Long businessId);

    void setManagers(List<ManagerDTO> managerList);

    ManagerDTO getManager(Long managerId);

    List<ManagerDTO> getAllManager();

    List<ManagerDTO> getManagersByBusiness(Long businessId);

    OrderDTO getOrder(Long orderId);

    List<OrderDTO> getOrderList(String orderCode, String itemCode, String itemName);

    List<BomDTO> getBomList();

    Map<String, Object> setMes(MesOpenApiDTO mesDTO);

    Map<String, Object> setMesList(List<MesOpenApiDTO> mesList);

}
