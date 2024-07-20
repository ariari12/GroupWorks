package kr.co.groupworks.control.kah.rest;


import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.HalfFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.service.kah.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacation")
public class VacationApiController {
    private final VacationService vacationService;


    // 연차 신청
    @PostMapping("/annual")
    public ResponseEntity<?> vacationAnnual(@Validated @RequestBody AnnualFormDTO dto,
                                            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO,
                                            Model model){

        log.info("AnnualFormDTO ={}",dto);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        // 검증에 실패하면 다시 입력 폼으로
//        ResponseEntity<Map<String, String>> BAD_REQUEST = errorResponseEntity(bindingResult, model, dto.getEmployeeId());
//        if (BAD_REQUEST != null) return BAD_REQUEST;
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("연차 신청이 성공적으로 처리되었습니다.");
    }

    // 반차 신청
    @PostMapping("/half")
    public ResponseEntity<?> vacationHalf(@Validated @RequestBody HalfFormDTO dto,
                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                                          Model model){

        log.info("HalfFormDTO ={}",dto);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        // 검증에 실패하면 다시 입력 폼으로
//        ResponseEntity<Map<String, String>> BAD_REQUEST = errorResponseEntity(bindingResult, model, dto.getEmployeeId());
//        if (BAD_REQUEST != null) return BAD_REQUEST;
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("반차 신청이 성공적으로 처리되었습니다.");
    }

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
