package kr.co.groupworks.control.kah.api;


import jakarta.validation.Valid;
import kr.co.groupworks.dto.cis.employee.SessionEmployeeDTO;
import kr.co.groupworks.dto.kah.AnnualFormDTO;
import kr.co.groupworks.dto.kah.HalfFormDTO;
import kr.co.groupworks.dto.kah.SickFormDTO;
import kr.co.groupworks.dto.kah.VacationMyHistoryDTO;
import kr.co.groupworks.service.kah.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("반차 신청이 성공적으로 처리되었습니다.");
    }

    // 병가 신청
    @PostMapping("/sick")
    @ResponseBody
    public ResponseEntity<?> vacationSick(@Valid @RequestPart("jsonData") SickFormDTO dto,
                                          @RequestPart("fileUpload") MultipartFile[] files,
                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO,
                                          Model model){

        log.info("SickFormDTO ={}, fileUpload ={}",dto, files);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());

        vacationService.save(dto,files);
        return ResponseEntity.status(HttpStatus.OK).body("병가 신청이 성공적으로 처리되었습니다.");
    }


}
