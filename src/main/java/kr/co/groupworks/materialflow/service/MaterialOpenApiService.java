package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.entity.Business;

import java.util.List;

public interface MaterialOpenApiService {
    Object getBusiness(Long businessId);

    boolean setBusinessList(List<Business> businessList);

    ManagerDTO getManager(Long managerId);
    List<ManagerDTO> getAllManager();
    List<ManagerDTO> getManagersByBusiness(Long businessId);

    void setManagers(List<ManagerDTO> managerList);
}
