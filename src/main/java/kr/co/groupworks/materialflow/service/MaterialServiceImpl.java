package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.materialflow.control.MaterialFlowManagerController;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.EmployeeDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.dto.OrderDTO;
import kr.co.groupworks.materialflow.entity.Business;
import kr.co.groupworks.materialflow.entity.BusinessManager;
import kr.co.groupworks.materialflow.entity.OrderClassification;
import kr.co.groupworks.materialflow.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final EmployeeRepository employeeRepository;
    private final BusinessManagerRepository managerRepository;
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final BomRepository bomRepository;
    private final MesRepository matesRepository;

    /* 전체 사원 정보 반환 */
    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll().stream().map(EmployeeDTO::new).toList();
    }

    /* 사원정보 추가 */
    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ManagerDTO setManager(ManagerDTO m, Long bId) {
        Business b = businessRepository.findById(bId).orElse(null);
        if(b == null) return null;
        return new ManagerDTO(managerRepository.save(m.setBusiness(new BusinessDTO(b)).dtoToEntity()));
    }

    /* 거래처 정보 추가 */
    @Override
    public BusinessDTO setBusiness(BusinessDTO business) {
        if(business == null) return null;
        return new BusinessDTO(businessRepository.save(business.dtoToEntity()));
    }

    @Override
    public Boolean setOrder(OrderDTO orderDTO) {
        if(orderDTO == null) return false;
        EmployeeDTO e = orderDTO.getEmployee();
        ManagerDTO m = orderDTO.getManager();
        if(e == null || m == null) return false;
        Long eId = e.getId();
        Long mId = m.getId();
        if(eId == null || mId == null) return false;
        Employee eE = employeeRepository.findById(eId).orElse(null);
        BusinessManager bm = managerRepository.findById(mId).orElse(null);
        if(eE == null || bm == null) return false;
        orderRepository.save(orderDTO.dtoToEntity(eE, bm));
        return true;
    }

    @Override
    public Map<String, List<OrderDTO>> getOrders() {
        return Map.of(
            MaterialFlowManagerController.receiveList, orderRepository.findByClassification(OrderClassification.RECEIVE).stream().map(OrderDTO::new).toList(),
            MaterialFlowManagerController.sendList, orderRepository.findByClassification(OrderClassification.SEND).stream().map(OrderDTO::new).toList()
        );
    }
}
