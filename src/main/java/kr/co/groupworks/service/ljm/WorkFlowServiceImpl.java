package kr.co.groupworks.service.ljm;

import kr.co.groupworks.dto.ljm.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.ljm.WorkFlowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkFlowServiceImpl implements WorkFlowService {
    private final WorkFlowRepository workFlowRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO getEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        return new EmployeeDTO()
                .setId(employee.getEmployeeId())
                .setName(employee.getEmployeeName())
                .setEmail(employee.getEmail())
                .set
    }

}
