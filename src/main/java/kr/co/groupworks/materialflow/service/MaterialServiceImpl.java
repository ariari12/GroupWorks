package kr.co.groupworks.materialflow.service;

import kr.co.groupworks.employee.repository.EmployeeRepository;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.EmployeeDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.entity.Business;
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
public class MaterialServiceImpl implements MaterialService {
    private final EmployeeRepository employeeRepository;
    private final BusinessManagerRepository managerRepository;
    private final OrderRepository orderRepository;
    private final BusinessRepository businessRepository;
    private final BomRepository bomRepository;
    private final MesRepository matesRepository;

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        return employeeRepository.findAll().stream().map(EmployeeDTO::new).toList();
    }

    @Override
    public boolean setManager(ManagerDTO m, Long bId) {
        Business b = businessRepository.findById(bId).orElse(null);
        if(b == null) return false;
        managerRepository.save(m.setBusiness(new BusinessDTO(b)).dtoToEntity());
        return true;
    }
}
