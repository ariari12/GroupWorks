package kr.co.groupworks.employee.service;

import kr.co.groupworks.calendar.entity.VacationHistory;
import kr.co.groupworks.calendar.repository.VacationHistoryRepository;
import kr.co.groupworks.department.dto.DepartmentDTO;
import kr.co.groupworks.department.entity.Department;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        employee = employeeRepository.save(employee);
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
//    받는사람, 참조하는 이메일 쿼리로 찾기
    @Override
    public List<String> getEmailsByQuery(String query) {
        return employeeRepository.findEmailsByQuery(query);
    }

//    DTO to Entity
    public Employee toEmployee(EmployeeDTO dto) {
        return Employee.builder()
                .employeeId(dto.getEmployeeId())
                .employeePW(dto.getEmployeePW())
                .employeeName(dto.getEmployeeName())
                .rankId(dto.getRankId())
                .rankName(dto.getRankName())
                .department(Department.builder()
                        .departmentId(dto.getDepartment().getDepartmentId())
                        .departmentName(dto.getDepartment().getDepartmentName())
                        .contactNumber(dto.getDepartment().getContactNumber())
                        .location(dto.getDepartment().getLocation())
                        .build())
                .email(dto.getEmail())
                .createdDate(dto.getJoinDate())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .salary(dto.getSalary())
                .supervisorId(dto.getSupervisorId())
                .build();
    }

//    Entity to DTO
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeePW(employee.getEmployeePW())
                .employeeName(employee.getEmployeeName())
                .rankId(employee.getRankId())
                .rankName(employee.getRankName())
                .department(DepartmentDTO.builder()
                        .departmentId(employee.getDepartment().getDepartmentId())
                        .departmentName(employee.getDepartment().getDepartmentName())
                        .contactNumber(employee.getDepartment().getContactNumber())
                        .location(employee.getDepartment().getLocation())
                        .build())
                .email(employee.getEmail())
                .joinDate(employee.getCreatedDate())
                .phoneNumber(employee.getPhoneNumber())
                .address(employee.getAddress())
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .supervisorId(employee.getSupervisorId())
                .build();
    }
}
