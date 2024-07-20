package kr.co.groupworks.control.kah;

import jakarta.validation.Valid;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.dto.kah.*;
import kr.co.groupworks.entity.kah.AmPm;
import kr.co.groupworks.service.cis.EmployeeService;
import kr.co.groupworks.service.kah.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/vacation")
@RequiredArgsConstructor
public class VacationController {
    private final EmployeeService employeeService;
    private final VacationService vacationService;

    // enum 상수 값 지속적으로 넘겨주기 위함
    @ModelAttribute("amPms")
    public AmPm[] amPms(){
        return AmPm.values();
    }

    @GetMapping("")
    public String vacationMain(@SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                               Model model) {
        log.info("VacationController - vacationMain");

        Long employeeId = sessionEmployeeDTO.getEmployeeId();
        log.info("세션 값 : "+employeeId);

        // 휴가 신청 내역 조회
        List<VacationMyHistoryDTO> vacationRequestList = vacationService.findAllByEmployeeId(employeeId);
        log.info("휴가 신청 내역 조회 : {}",vacationRequestList);

        // 휴가 보유 사용현황 조회


        // 검증 validation th:filed 속성 추가하기 위한 dto
        model.addAttribute("annualForm",new AnnualFormDTO());
        model.addAttribute("halfForm",new HalfFormDTO());
        model.addAttribute("otherForm",new OtherFormDTO());
        model.addAttribute("sickForm",new SickFormDTO());
        model.addAttribute("vacationRequestList",vacationRequestList);
        // header title 넘겨주기
        model.addAttribute("title", "내 휴가");
        model.addAttribute("subtitle", "휴가 신청내역");
        return "kah/vacationMain";
    }

    @GetMapping(value = "/team")
    public String vacationTeam(Model model) {
        log.info("VacationController - vacationTeam");

        // header title 넘겨주기
        model.addAttribute("title", "구성원 휴가");
        return "kah/vacationTeam";
    }


    // 연차 신청
    @PostMapping("/annual")
    @ResponseBody
    public ResponseEntity<?> vacationAnnual(@Validated @RequestBody AnnualFormDTO dto, BindingResult bindingResult,
                                 @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                                 Model model){

        log.info("AnnualFormDTO ={}",dto);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        // 검증에 실패하면 다시 입력 폼으로
        ResponseEntity<Map<String, String>> BAD_REQUEST = errorResponseEntity(bindingResult, model, dto.getEmployeeId());
        if (BAD_REQUEST != null) return BAD_REQUEST;
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("연차 신청이 성공적으로 처리되었습니다.");
    }

    // 반차 신청
    @PostMapping("/half")
    @ResponseBody
    public ResponseEntity<?> vacationHalf(@Validated @RequestBody HalfFormDTO dto, BindingResult bindingResult,
                                            @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                                            Model model){

        log.info("HalfFormDTO ={}",dto);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        // 검증에 실패하면 다시 입력 폼으로
        ResponseEntity<Map<String, String>> BAD_REQUEST = errorResponseEntity(bindingResult, model, dto.getEmployeeId());
        if (BAD_REQUEST != null) return BAD_REQUEST;
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("반차 신청이 성공적으로 처리되었습니다.");
    }


//    @PostMapping("/sick")
//    @ResponseBody
//    public ResponseEntity<?> vacationSick(@Validated @RequestBody SickFormDTO dto, BindingResult bindingResult,
//                                            @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
//                                            Model model){
//
//        log.info("AnnualFormDTO ={}",dto);
//        Long employeeId = sessionEmployeeDTO.getEmployeeId();
//        // 검증에 실패하면 다시 입력 폼으로
//        ResponseEntity<Map<String, String>> BAD_REQUEST = errorResponseEntity(bindingResult, model, employeeId);
//        if (BAD_REQUEST != null) return BAD_REQUEST;
//        vacationService.save(dto, employeeId);
//        return ResponseEntity.status(HttpStatus.OK).body("병가 신청이 성공적으로 처리되었습니다.");
//    }
//
//    @PostMapping("/other")
//    @ResponseBody
//    public ResponseEntity<?> vacationOther(@Validated @RequestBody AnnualFormDTO dto, BindingResult bindingResult,
//                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
//                                          Model model){
//
//        log.info("AnnualFormDTO ={}",dto);
//        Long employeeId = sessionEmployeeDTO.getEmployeeId();
//        // 검증에 실패하면 다시 입력 폼으로
//        ResponseEntity<Map<String, String>> BAD_REQUEST = errorResponseEntity(bindingResult, model, employeeId);
//        if (BAD_REQUEST != null) return BAD_REQUEST;
//        vacationService.save(dto, employeeId);
//        return ResponseEntity.status(HttpStatus.OK).body("기타 휴가 신청이 성공적으로 처리되었습니다.");
//    }



    private ResponseEntity<Map<String, String>> errorResponseEntity(BindingResult bindingResult, Model model, Long employeeId) {
        if (bindingResult.hasErrors()) {
            // 휴가 신청 내역 조회 (이걸 안해주면 조회를 안해 줌)
            List<VacationMyHistoryDTO> vacationRequestList = vacationService.findAllByEmployeeId(employeeId);
            model.addAttribute("vacationRequestList",vacationRequestList);
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return null;
    }


}
