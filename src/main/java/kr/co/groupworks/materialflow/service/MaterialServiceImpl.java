package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.materialflow.control.MaterialFlowManagerController;
import kr.co.groupworks.materialflow.dto.*;
import kr.co.groupworks.materialflow.entity.*;
import kr.co.groupworks.materialflow.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private final MesRepository masRepository;
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
        if(e == null) return returnMessage("사원 담당자 정보가 비어있습니다.", false);
        if(m == null) return returnMessage("거래처 담당자 정보가 비어있습니다.", false);

        Long eId = e.getId(), mId = m.getId();
        if(eId == null) return returnMessage("사원 담당자 정보가 비어있습니다.", false);
        if(mId == null) return returnMessage("거래처 담당자 정보가 비어있습니다.", false);

        Employee eE = employeeRepository.findById(eId).orElse(null);
        BusinessManager bm = managerRepository.findById(mId).orElse(null);
        if(eE == null) return returnMessage("사원 담당자 정보가 올바르지 않습니다.", false);
        if(bm == null) return returnMessage("거래처 담당자 정보가 올바르지 않습니다.", false);

        /* 품목 유효성 체크 */
        List<BomDTO> bomDTOList = orderDTO.getBomList();
        if(bomDTOList == null || bomDTOList.isEmpty()) return returnMessage("품목 정보가 비어있습니다.", false);
        List<Bom> bomList = bomDTOList.stream().map(BomDTO::dtoToEntity).toList();

        /*
         * 고유 번호: (발주:0A, 수주 0B) + 작성시각 (ms),분,초
         * 랜덤코드: 정수와 영문 대문자 포함 랜덤3자리
         * 주문코드: 고유 번호 + "-" + 랜덤코드 + "품목개수"
         * 품목코드: 주문코드 + "-" + n(주문의 n번째 품목), example: 0A13733178-3AB7-2
         * 자재코드: 품목코드 + n(품목코드내 n번째 자재) + 자재고유Id,
         * 자재코드 example: 0A13733178-3AB7-2131 = "0A13733178-3AB7-2" 품목의 1번째 자재, 고유Id: 31
         */
        bomList.forEach(b -> {
            /* 자재리스트 생성 */
            List<MaterialItem> itemList = new ArrayList<>();
            for (int i = 1; i <= b.getQuantity(); i++) {
                itemList.add(new MaterialItemDTO(new MaterialItem())
                        .setId(0).setItemStatus(null).setItemCode(b.getItemCode() + i)
                        .dtoToEntity());
            }
            /* 각 BOM 자재 List 저장 */
            b.setItemList(materialItemRepository.saveAll(
                    /* 자재 코드 업데이트 */
                    materialItemRepository.saveAll(itemList).stream().map(i ->
                            new MaterialItemDTO(i).setItemCode(i.getItemCode() + i.getId())
                            .dtoToEntity()).toList()));
        });
        orderRepository.save(orderDTO.dtoToEntity(eE, bm).setBomList(bomRepository.saveAll(bomList)));
        return returnMessage("발주서/수주서 등록 완료", true);
    }

    @Override
    public Map<String, List<OrderDTO>> getOrders() {
        return Map.of(
            MaterialFlowManagerController.RECEIVE_LIST, orderRepository.findByClassification(OrderClassification.RECEIVE).stream().map(OrderDTO::new).toList(),
            MaterialFlowManagerController.SEND_LIST, orderRepository.findByClassification(OrderClassification.SEND).stream().map(OrderDTO::new).toList()
        );
    }

    @Override
    public List<OrderBomListVO> getBomList() {
        return orderRepository.findByBomList().stream().map(OrderBomListVO::new).toList();
    }

    @Override
    public List<MaterialItemVO> getItemList(long bomId) {
        return materialItemRepository.findByBomId(bomId).stream().map(MaterialItemVO::new).toList();
    }

    @Override
    public Map<String, Object> deleteOrder(Long orderId) {
        Order o = orderRepository.findById(orderId).orElse(null);
        if(o == null) return returnMessage("발주서/수주서를 찾을 수 없습니다.", false);
        if (orderRepository.orderDeleteCheck(orderId))
            return returnMessage("발주/수주가 진행되기 전에만 삭제가 가능합니다.", false);
        orderRepository.delete(o);
        if(orderRepository.findById(orderId).orElse(null) != null)
            return returnMessage("발주서/수주서를 삭제할 수 없습니다", false);
        return returnMessage("발주서/수주서가 삭제되었습니다.", true);
    }

    @Override
    public Map<String, Object> deleteManager(Long managerId) {
        if(orderRepository.findByManagerId(managerId).isEmpty()) {
            managerRepository.deleteById(managerId);
            if (managerRepository.findById(managerId).orElse(null) == null)
                return returnMessage("거래처 담당자 정보가 삭제되었습니다.", true);
        }
        return returnMessage("거래처 담당자 정보가 사용되는 발주/수주 기록이 존재하여\n거래처 담당자 정보를 삭제하지 못하였습니다.", false);
    }

    @Override
    public Map<String, Object> deleteBusiness(Long businessId) {
        if(managerRepository.findByBusinessId(businessId).isEmpty()) {
            businessRepository.deleteById(businessId);
            if (businessRepository.findById(businessId).orElse(null) == null)
                return returnMessage("거래처 정보가 삭제되었습니다.", true);
            return returnMessage("거래처 정보를 삭제하지 못하였습니다.", false);
        }
        return returnMessage("거래처 담당자 정보가 남아있어 거래처 정보를 삭제할 수 없습니다.", false);
    }

    @Override
    public Map<String, Object> updateItems(MaterialStatusUpdateDTO itemDTO) {
        if(itemDTO.getItemCodeList().isEmpty()) return returnMessage("선택된 자재가 없습니다.", false);

        List<MaterialItem> findItemList = materialItemRepository.findByItemCodeIn(itemDTO.getItemCodeList());
        if(findItemList.isEmpty()) return returnMessage("자재를 찾을 수 없습니다.", false);

        materialItemRepository.saveAll(findItemList.stream().map(item -> itemDTO.convertToDTO(new MaterialItemDTO(item))
                .dtoToEntity()).toList());
        return returnMessage("자재 상태 정보가 변경되었습니다.", true);
    }

    @Override
    public Map<String, Object> updateItem(Long itemId, int statusCode) {
        MaterialItem item = materialItemRepository.findById(itemId).orElse(null);
        if(item == null) return returnMessage("해당 자재를 찾을 수 없습니다.", false);

        MaterialItem result = null;
        if(ItemStatus.getItemStatus(statusCode) == ItemStatus.STOCK_ENTRY) { // 입고
            result = materialItemRepository.save(new MaterialItemDTO(item)
                    .setStorageTime(LocalDate.now()).setItemStatus(ItemStatus.getItemStatus(statusCode))
                    .dtoToEntity());
        } else if (ItemStatus.getItemStatus(statusCode) == ItemStatus.ISSUING) { // 출고
            result = materialItemRepository.save(new MaterialItemDTO(item)
                    .setDeliveryTime(LocalDate.now()).setItemStatus(ItemStatus.getItemStatus(statusCode))
                    .dtoToEntity());
        }
        else return returnMessage("변경 상태정보가 올바르지 않습니다.", false);

        Bom bom = bomRepository.findById(result.getBomId()).orElse(null);
        if(bom == null) return returnMessage("Bom 정보를 찾을 수 없습니다.", false);
        
        return Map.of("message", "자재 상태 정보가 성공적으로 등록되었습니다.",
                "result", true,
                "url", "/materialflow/item/" + bom.getId() + "/" + bom.getItemCode() + "/" + bom.getItemName()
        );
    }

    @Override
    public Map<String, Object> orderCompleteCheck(Long bomId, int stat) {
        if(orderRepository.orderCompleteCheck(bomId, stat)) {
            Order order = orderRepository.findByBomId(bomId);
            if(order == null) return returnMessage("발주/수주 목록을 찾을 수 없습니다.", false);
            return returnMessage(stat == 1 ? "품목 발주가 완료되었습니다." : "품목 수주가 완료되었습니다.", true);
        }
        return returnMessage(stat == 1 ? "품목자재가 모두 발주 되지 않았습니다." : "품목자재가 모두 수주 되지 않았습니다.", false);
    }

    @Override
    public Map<String, Object> getBomSMS(Long bomId) {
        Bom b = bomRepository.findById(bomId).orElse(null);
        log.info("bom; {}", b);

        if(b == null) return returnMessage("품목 기록을 찾을 수 없습니다.", false);
        bomRepository.save(new BomDTO(b).setStatus(true).dtoToEntity());

        Order order = orderRepository.findByBomId(bomId);
        if(order == null) return returnMessage("발주/수주 기록을 찾을 수 없습니다.", false);

        order = orderRepository.save(new OrderDTO(order).setDeliveryDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(OrderDTO.DATE_FORMAT)))
                .dtoToEntity(employeeRepository.findById(order.getEmployee().getEmployeeId()).orElse(null)));
        return Map.of("result", true, "order", new OrderDTO(order));
    }

    @Override
    public boolean getBomStatus(long bomId) {
        return bomRepository.findStatusById(bomId);
    }

    private Map<String, Object> returnMessage(String message, boolean result) {
        return Map.of("message", message, "result", result);
    }

    private Map<String, Object> returnMessage(String message, Object result) {
        return Map.of("message", message, "result", result);
    }
}
