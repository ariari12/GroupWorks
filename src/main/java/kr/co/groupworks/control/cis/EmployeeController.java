package kr.co.groupworks.control.cis;

import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.cis.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employee);
    }
}
