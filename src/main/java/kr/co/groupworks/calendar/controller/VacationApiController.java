package kr.co.groupworks.calendar.controller;
import kr.co.groupworks.calendar.dto.*;
import kr.co.groupworks.calendar.service.VacationService;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacation")
public class VacationApiController {
    private final VacationService vacationService;


    // 연차 신청
    @PostMapping("/annual")
    public ResponseEntity<?> vacationAnnual(@Validated @RequestBody AnnualFormDTO dto,
                                            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("연차 신청이 성공적으로 처리되었습니다.");
    }

    // 반차 신청
    @PostMapping("/half")
    public ResponseEntity<?> vacationHalf(@Validated @RequestBody HalfFormDTO dto,
                                          @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO){

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("반차 신청이 성공적으로 처리되었습니다.");
    }

    // 병가 신청
    @ResponseBody
    @PostMapping("/sick")
    public ResponseEntity<?> vacationSick(@Validated @RequestPart("jsonData") SickFormDTO dto,
                                          @RequestPart("sickFileUpload") MultipartFile[] files,
                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO){

        log.info("SickFormDTO ={}, fileUpload ={}",dto, files);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());

        vacationService.save(dto,files);
        return ResponseEntity.status(HttpStatus.OK).body("병가 신청이 성공적으로 처리되었습니다.");
    }


    // 기타 휴가 신청
    @ResponseBody
    @PostMapping("/other")
    public ResponseEntity<?> vacationOther(@Validated @RequestPart("jsonData") OtherFormDTO dto,
                                          @RequestPart(value = "otherFileUpload") MultipartFile[] files,
                                          @SessionAttribute(name = "employee")SessionEmployeeDTO sessionEmployeeDTO){


        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());

        vacationService.save(dto,files);
        return ResponseEntity.status(HttpStatus.OK).body("기타 휴가 신청이 성공적으로 처리되었습니다.");
    }

    //휴가 승인 반려
    @ResponseBody
    @PostMapping(value = "/team/approval")
    public void vacationTeamApproval(@RequestBody ApprovalRequestDTO approvalRequest,
                                                  @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        vacationService.approvalVacation(approvalRequest.getCalendarId(), approvalRequest.getStatus(), sessionEmployeeDTO.getEmployeeId());
    }

}
