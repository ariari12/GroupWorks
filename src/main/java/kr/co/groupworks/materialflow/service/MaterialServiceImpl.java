package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.materialflow.control.MaterialFlowManagerController;
import kr.co.groupworks.materialflow.dto.*;
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
    private final MaterialItemRepository materialItemRepository;

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
    public Map<String, Object> setOrder(OrderDTO orderDTO) {
        if(orderDTO == null) return returnMessage("발주서/수주서 내용이 비어있습니다.", false);

        /* 담당자 유효성 체크 */
        EmployeeDTO e = orderDTO.getEmployee();
        ManagerDTO m = orderDTO.getManager();
        if(e == null || m == null) return returnMessage("담당자 정보가 비어있습니다.", false);
        Long eId = e.getId();
        Long mId = m.getId();
        if(eId == null || mId == null) return returnMessage("담당자 정보가 비어있습니다.", false);
        Employee eE = employeeRepository.findById(eId).orElse(null);
        BusinessManager bm = managerRepository.findById(mId).orElse(null);
        if(eE == null || bm == null) return returnMessage("담당자 정보가 올바르지 않습니다.", false);

        /* 품목 유효성 체크 */
        List<BomDTO> bList = orderDTO.getBomList();
        if(bList == null || bList.isEmpty()) return returnMessage("품목 정보가 비어있습니다.", false);
//        List<Bom> bomList = bomRepository.saveAll(bList.stream().map(BomDTO::dtoToEntity).toList());
//        if(bomList.isEmpty()) return returnMessage("품목 정보를 저장할 수 없습니다.", false);

        orderRepository.save(orderDTO.setBomList(bList).dtoToEntity(eE, bm));
        return returnMessage("발주서/수주서 등록 완료", true);
    }

    @Override
    public Map<String, List<OrderDTO>> getOrders() {
        return Map.of(
            MaterialFlowManagerController.receiveList, orderRepository.findByClassification(OrderClassification.RECEIVE).stream().map(OrderDTO::new).toList(),
            MaterialFlowManagerController.sendList, orderRepository.findByClassification(OrderClassification.SEND).stream().map(OrderDTO::new).toList()
        );
    }

    @Override
    public List<OrderBomListVO> getBomList() {
        return orderRepository.findByBomList().stream().map(OrderBomListVO::new).toList();
    }

    @Override
    public List<MaterialItemDTO> getItemList(long bomId) {
        return materialItemRepository.findByBomId(bomId).stream().map(MaterialItemDTO::new).toList();
    }

    private Map<String, Object> returnMessage(String message, boolean result) {
        return Map.of("message", message, "result", result);
    }
}
