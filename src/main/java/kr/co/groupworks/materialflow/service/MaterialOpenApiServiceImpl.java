package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.entity.Business;
import kr.co.groupworks.materialflow.repository.BomRepository;
import kr.co.groupworks.materialflow.repository.BusinessRepository;
import kr.co.groupworks.materialflow.repository.MesRepository;
import kr.co.groupworks.materialflow.repository.OrderRepository;
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
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final BomRepository bomRepository;
    private final MesRepository MesRepository;

    @Override
    public Object getBusiness(Long businessId) {
        if (businessId == null) {
            return businessRepository.findByIdGreaterThan(0L).stream().map(BusinessDTO::new).toList();
        }
        Business b = businessRepository.findById(businessId).orElse(null);
        return b != null ? new BusinessDTO(b) : null;
    }

    @Override
    public boolean setBusinessList(List<Business> businessList) {
        if (businessList == null) return false;
        businessRepository.saveAll(businessList);
        return true;
    }
}
