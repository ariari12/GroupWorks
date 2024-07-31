package kr.co.groupworks.control.cis;

import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.dto.cis.employee.EmployeeDTO;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.service.cis.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    사원 저장
    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        log.info("새로운 사원 추가 : " + employeeDTO.toString());

        String encPassword = bCryptPasswordEncoder.encode(employeeDTO.getEmployeePW());
        employeeDTO.setEmployeePW(encPassword);
        employeeService.saveEmployee(employeeDTO);
        System.out.println(employeeDTO.toString() + "입력");
        return ResponseEntity.ok().body(employeeDTO);
    }

//    사원 목록
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> list = employeeService.findAll();
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
        return ResponseEntity.ok().body(list);

    }

//    사원 디테일
    @GetMapping("/list/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String employeeId) {
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(Long.valueOf(employeeId));
        System.out.println(employeeDTO.getEmployeeName());
        return ResponseEntity.ok().body(employeeDTO);
    }

//    비밀번호 변경
    @PutMapping("/modify")
    public ResponseEntity<EmployeeDTO> modifyEmployee(@RequestBody Map<String, String> request, HttpSession session) {
        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        log.info(sessionEmployeeDTO.getEmployeeName() + " 비밀번호 변경 컨트롤러");

        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());


        // 현재 비밀번호 확인 로직
        if(employeeService.isEqualPassword(request.get("employeePW"), employeeDTO.getEmployeePW())) {
            // 새 비밀번호 설정 및 저장
            employeeDTO.setEmployeePW(bCryptPasswordEncoder.encode(request.get("newEmployeePW")));
            employeeService.saveEmployee(employeeDTO);
            return ResponseEntity.ok().body(employeeDTO);
        }else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}


