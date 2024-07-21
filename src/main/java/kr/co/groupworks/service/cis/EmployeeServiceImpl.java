package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = toEmployee(employeeDTO);

        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByEmployeeId(Long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Employee findByEmployeeEmail(String employeeEmail) {
        return employeeRepository.findByEmail(employeeEmail);
    }

    @Override
    public boolean findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name) {
        if(employeeRepository.findByEmployeeIdAndEmployeePWAndEmployeeName(emplyoeeId,pw,name) != null)
            return true;
        else return false;
    }

    public Employee toEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .employeeId(dto.getEmployeeId())
                .employeePW(dto.getEmployeePW())
                .employeeName(dto.getEmployeeName())
                .rankId(dto.getRankId())
                .rankName(dto.getRankName())
                .departmentId(dto.getDepartmentId())
                .departmentName(dto.getDepartmentName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .joinDate(dto.getJoinDate())
                .salary(dto.getSalary())
                .supervisorId(dto.getSupervisorId())
                .build();
    }

}
