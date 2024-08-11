package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.materialflow.dto.*;
import kr.co.groupworks.materialflow.entity.Business;
import kr.co.groupworks.materialflow.entity.BusinessManager;
import kr.co.groupworks.materialflow.entity.Order;
import kr.co.groupworks.materialflow.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MaterialOpenApiServiceImpl implements MaterialOpenApiService {
    private final BusinessManagerRepository managerRepository;
    private final BusinessRepository businessRepository;
    private final OrderRepository orderRepository;
    private final BomRepository bomRepository;
    private final MesRepository MesRepository;

    /* 거래처 정보 반환 */
    @Override
    public Object getBusiness(Long businessId) {
        /* 거래처 정보 반환 id:1 = 본사 정보 */
        if (businessId == null) return businessRepository.findByIdGreaterThan(1L).stream().map(BusinessDTO::new).toList();
        Business b = businessRepository.findById(businessId).orElse(null);
        return b != null ? new BusinessDTO(b) : null;
    }

    /*  */
    @Override
    public boolean setBusinessList(List<Business> businessList) {
        if (businessList == null) return false;
        businessRepository.saveAll(businessList);
        return true;
    }

    @Override
    public ManagerDTO getManager(Long managerId) {
        BusinessManager bm = managerRepository.findById(managerId).orElse(null);
        return bm == null ? null : new ManagerDTO(bm);
    }

    @Override
    public List<ManagerDTO> getAllManager() {
        return managerRepository.findAll().stream().map(ManagerDTO::new).toList();
    }

    @Override
    public List<ManagerDTO> getManagersByBusiness(Long businessId) {
        return managerRepository.findByBusiness_Id(businessId).stream().map(ManagerDTO::new).toList();
    }

    @Override
    public void setManagers(List<ManagerDTO> managerList) {
        managerRepository.saveAll(managerList.stream().map(ManagerDTO::dtoToEntity).toList());
    }

    @Override
    public List<OrderDTO> getOrderList(String orderCode, String itemCode, String itemName) {
        return orderRepository.findByOrderCodeAndItemCodeAndItemName(orderCode, itemCode, itemName)
                .stream().map(OrderDTO::new).toList();
    }

    @Override
    public List<MesDTO> setMesList(List<MesDTO> mesList) {
        return MesRepository.saveAll(mesList.stream().map(MesDTO::dtoToEntity).toList())
                .stream().map(MesDTO::new).toList();
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Order o = orderRepository.findById(orderId).orElse(null);
        if(o == null) return null;
        return new OrderDTO(o);
    }

    @Override
    public List<BomDTO> getBomList() {
        return bomRepository.findAll().stream().map(BomDTO::new).toList();
    }

}
