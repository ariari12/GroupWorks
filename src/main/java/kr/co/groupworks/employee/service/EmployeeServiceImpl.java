package kr.co.groupworks.employee.service;

import kr.co.groupworks.calendar.entity.VacationHistory;
import kr.co.groupworks.calendar.repository.VacationHistoryRepository;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Employee employee = employeeDTO.toEmployee();
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
        EmployeeDTO employeeDTO = employee.toEmployeeDTO();

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
    //    받는사람, 참조하는 이메일 쿼리로 찾는 서비스 로직
    @Override
    public List<String> getEmailsByQuery(String query) {
        return employeeRepository.findEmailsByQuery(query);
    }

    //    부서번호에 해당하는 사원 목록 가져오는 서비스 로직
    @Override
    public List<EmployeeDTO> findByDepartmentId(Long departmentId) {
        List<Employee> employeeList = employeeRepository.findEmployeeByDepartment_DepartmentId(departmentId);
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(Employee employee : employeeList)
        {
            EmployeeDTO employeeDTO = employee.toEmployeeDTO();
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }

    //    해당 부서에 존재하는 직급 번호 가져오는 서비스 로직
    @Override
    public List<Integer> findRankIdByDepartmentId(Long departmentId) {
        List<Integer> rankIdList = employeeRepository.findRankIdByDepartmentId(departmentId);
        return rankIdList;
    }

    //    사수 employee 정보 가져오기
    @Override
    public EmployeeDTO findSupervisorEmployeeByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findSupervisorEmployeeByEmployeeId(employeeId);
        EmployeeDTO employeeDTO = employee.toEmployeeDTO();
        return employeeDTO;
    }

    @Transactional
    @Override
    public void updatePhoneNumberByEmployee(EmployeeDTO employeeDTO) {
        Long employeeId = employeeDTO.getEmployeeId();
        String phoneNumber = employeeDTO.getPhoneNumber();
        employeeRepository.updatePhoneNumberByEmployeeId(employeeId,phoneNumber);

    }

    @Override
    public void updateAddressByEmployee(EmployeeDTO employeeDTO) {
        Long employeeId = employeeDTO.getEmployeeId();
        String address = employeeDTO.getAddress();
        employeeRepository.updateAddressByEmployeeId(employeeId,address);

    }





}
