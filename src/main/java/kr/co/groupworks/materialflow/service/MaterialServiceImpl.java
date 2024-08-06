package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.materialflow.dto.EmployeeDTO;
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
public class MaterialServiceImpl implements MaterialService {
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final BomRepository bomRepository;
    private final MesRepository matesRepository;

    @Override
    public List<EmployeeDTO> getAllManager() {
        return employeeRepository.findAll().stream().map(EmployeeDTO::new).toList();
    }
}
