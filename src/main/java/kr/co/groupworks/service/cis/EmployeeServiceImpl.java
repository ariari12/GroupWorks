package kr.co.groupworks.service.cis;

import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.entity.kah.VacationHistory;
import kr.co.groupworks.repository.cis.EmployeeRepository;
import kr.co.groupworks.repository.kah.VacationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final VacationHistoryRepository vacationHistoryRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    사원 저장 서비스
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = toEmployee(employeeDTO);
        employee=employeeRepository.save(employee);
        //휴가 내역 초기화
        VacationHistory vacationHistory = VacationHistory.createFromEmployee(employee);
        vacationHistoryRepository.save(vacationHistory);
    }

//    사원 목록 불러오기
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

//    아이디로 사원 찾기
    @Override
    public EmployeeDTO findByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeDTO employeeDTO = toEmployeeDTO(employee);

        return employeeDTO;
    }

//    이메일로 사원 찾기
    @Override
    public Employee findByEmployeeEmail(String employeeEmail) {
        return employeeRepository.findByEmail(employeeEmail);
    }

//    아이디와 비밀번호와 이름으로 사원찾기
    @Override
    public boolean findByEmployeeIdAndEmployeePWAndEmployeeName(Long emplyoeeId, String pw, String name) {
        if(employeeRepository.findByEmployeeIdAndEmployeePWAndEmployeeName(emplyoeeId,pw,name) != null)
            return true;
        else return false;
    }

//    비밀번호 변경 시 비밀번호가 일치하는지 확인하는 서비스 로직
    @Override
    public boolean isEqualPassword(String checkPW, String currentPW) {
        if(bCryptPasswordEncoder.matches(checkPW, currentPW))
        {
            return true;
        }else
        {
            return false;
        }

    }

//    Entity to DTO
    public Employee toEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .employeeId(dto.getEmployeeId())
                .employeePW(dto.getEmployeePW())
                .employeeName(dto.getEmployeeName())
                .rankId(dto.getRankId())
                .rankName(dto.getRankName())
                .department(Department.builder()
                        .departmentId(dto.getDepartmentId())
                        .departmentName(dto.getDepartmentName())
                        .build())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .joinDate(dto.getJoinDate())
                .salary(dto.getSalary())
                .supervisorId(dto.getSupervisorId())
                .build();
    }

//    DTO to Entity
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeePW(employee.getEmployeePW())
                .employeeName(employee.getEmployeeName())
                .rankId(employee.getRankId())
                .rankName(employee.getRankName())
                .departmentId(employee.getDepartment().getDepartmentId())
                .departmentName(employee.getDepartment().getDepartmentName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .joinDate(employee.getJoinDate())
                .salary(employee.getSalary())
                .supervisorId(employee.getSupervisorId())
                .build();
    }
}
