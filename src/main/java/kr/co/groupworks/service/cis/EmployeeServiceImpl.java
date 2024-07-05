package kr.co.groupworks.service.cis;

import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
