package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.materialflow.repository.BomRepository;
import kr.co.groupworks.materialflow.repository.BusinessRepository;
import kr.co.groupworks.materialflow.repository.MesRepository;
import kr.co.groupworks.materialflow.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return businessRepository.findAll();
        }
        return businessRepository.findById(businessId).orElse(null);
    }
}
