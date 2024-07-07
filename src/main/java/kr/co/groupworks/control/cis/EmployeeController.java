package kr.co.groupworks.control.cis;

import kr.co.groupworks.entity.cis.Employee;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.cis.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        System.out.println(employee.toString() + "입력");
        return ResponseEntity.ok().body(employee);
    }
}
