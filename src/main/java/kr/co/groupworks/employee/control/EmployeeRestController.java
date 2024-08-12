package kr.co.groupworks.employee.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Hidden
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeRestController {


    private final EmployeeService employeeService;

//    private final DefaultMessageService messageService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



//    public EmployeeRestController() {
//        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
//        this.messageService = NurigoApp.INSTANCE.initialize("NCS5YXRIXQPF7INV", "FPPRUKWMCG6N3S9SNWSNQG05QOX49WVR", "https://api.coolsms.co.kr");
//    }
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

//    @PostMapping("/send-one")
//    public SingleMessageSentResponse sendOne() {
//        Message message = new Message();
//        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
//        message.setFrom("01082307981");
//        message.setTo("수신번호 입력");
//        message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다.");
//
//        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        System.out.println(response);
//
//        return response;
//    }

}


