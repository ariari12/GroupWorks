package kr.co.groupworks.employee.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.EmployeeDTO;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    사원 저장
    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

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

    private final DefaultMessageService messageService;
    private final AES256TextEncryptor encryptor;
    @Value("${test.phone}")
    String fromNumber;
//    핸드폰 변경 시 문자 인증 로직
    @PostMapping("/send-one")
    public SingleMessageSentResponse sendOne(@RequestBody Map<String, String> request) {
        String newPhoneNumber = request.get("phoneNumber").replaceAll("-","");
        Message message = new Message();
        message.setFrom(encryptor.decrypt(fromNumber));
        message.setTo(newPhoneNumber);
        String certificationNumber = request.get("certificationNumber");
        message.setText("Group_Workers 휴대폰 인증 : 다음 번호를 입력해주세요 (" + certificationNumber + ")");

        return messageService.sendOne(new SingleMessageSendingRequest(message));
    }

//    핸드폰 번호 변경 로직
    @PutMapping("/modifyphonenumber")
    public ResponseEntity<String> modifyPhoneNumber(@RequestBody Map<String, String> request, HttpSession session) {
        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());

        String newPhoneNumber = request.get("newPhoneNumber");;
        employeeDTO.setPhoneNumber(newPhoneNumber);
        employeeService.updatePhoneNumberByEmployee(employeeDTO);
        // 성공적으로 업데이트되면 200 OK 응답을 반환합니다.
        return ResponseEntity.ok("핸드폰 번호가 성공적으로 변경되었습니다.");
    }

//    주소 변경 로직
    @PutMapping("/modifyaddress")
    public ResponseEntity<String> modifyAddress(@RequestBody Map<String, String> request, HttpSession session) {
        SessionEmployeeDTO sessionEmployeeDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        EmployeeDTO employeeDTO = employeeService.findByEmployeeId(sessionEmployeeDTO.getEmployeeId());

        String newAddress = request.get("newAddress");
        employeeDTO.setAddress(newAddress);
        employeeService.updateAddressByEmployee(employeeDTO);

        // 성공적으로 업데이트되면 200 OK 응답을 반환합니다.
        return ResponseEntity.ok("주소가 성공적으로 변경되었습니다.");
    }
}


