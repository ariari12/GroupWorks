package kr.co.groupworks.service.cis;

import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmployeeId(Integer employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    @Override
    public boolean findByEmployeeIdAndEmployeePWAndEmployeeName(String emplyoeeId, String pw, String name) {
        if(employeeRepository.findByEmployeeIdAndEmployeePWAndEmployeeName(emplyoeeId,pw,name) != null)
            return true;
        else return false;
    }

}
