package kr.co.groupworks.materialflow.service;


import kr.co.groupworks.materialflow.dto.EmployeeDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;

import java.util.List;

public interface MaterialService {
    List<EmployeeDTO> getAllEmployee();

    boolean setManager(ManagerDTO manager, Long businessId);
}
