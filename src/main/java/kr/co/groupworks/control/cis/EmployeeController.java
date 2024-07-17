package kr.co.groupworks.control.cis;

import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.cis.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        log.info("새로운 사원 추가 : " + employee.toString());

        String encPassword = bCryptPasswordEncoder.encode(employee.getEmployeePW());
        employee.setEmployeePW(encPassword);
        employeeService.saveEmployee(employee);
        System.out.println(employee.toString() + "입력");
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> list = employeeService.findAll();
        for (Employee employee : list) {
            System.out.println(employee.toString());
        }
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/list/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeService.findByEmployeeId(Long.valueOf(employeeId));
        System.out.println(employee.getEmployeeName());
        return ResponseEntity.ok().body(employee);
    }
}
