package kr.co.groupworks.calendar.controller;
import io.swagger.v3.oas.annotations.Hidden;
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
@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/vacation")
public class VacationApiController {

    private final VacationService vacationService;

    @PostMapping("/annual")
    public ResponseEntity<String> vacationAnnual(
            @RequestBody @Validated AnnualFormDTO dto,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("연차 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/half")
    public ResponseEntity<String> vacationHalf(
            @RequestBody @Validated HalfFormDTO dto,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body("반차 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/sick")
    public ResponseEntity<String> vacationSick(
            @RequestPart("jsonData") @Validated SickFormDTO dto,
            @RequestPart(value = "sickFileUpload", required = false) MultipartFile[] files,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto, files);
        return ResponseEntity.status(HttpStatus.OK).body("병가 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/other")
    public ResponseEntity<String> vacationOther(
            @RequestPart("jsonData") @Validated OtherFormDTO dto,
            @RequestPart(value = "otherFileUpload", required = false) MultipartFile[] files,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto, files);
        return ResponseEntity.status(HttpStatus.OK).body("기타 휴가 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/team/approval")
    public ResponseEntity<Void> vacationTeamApproval(
            @RequestBody ApprovalRequestDTO approvalRequest,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        vacationService.approvalVacation(approvalRequest.getCalendarId(), approvalRequest.getStatus(), sessionEmployeeDTO.getEmployeeId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
