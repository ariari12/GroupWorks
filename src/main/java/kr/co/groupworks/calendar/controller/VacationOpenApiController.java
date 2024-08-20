package kr.co.groupworks.calendar.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.groupworks.calendar.dto.*;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.calendar.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Tag(name = "휴가 관리 (Vacation) OpenAPI")
@RestController
@RequestMapping("/openapi/vacation")
@RequiredArgsConstructor
public class VacationOpenApiController {

    private final VacationService vacationService;

    @PostMapping("/annual")
    @Operation(summary = "연차 신청", description = "사원 연차를 신청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "연차 신청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<String> vacationAnnual(
            @Parameter(description = "연차 신청 데이터", required = true) @RequestBody AnnualFormDTO dto,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.ok("연차 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/half")
    @Operation(summary = "반차 신청", description = "사원 반차를 신청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "반차 신청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<String> vacationHalf(
            @Parameter(description = "반차 신청 데이터", required = true) @RequestBody HalfFormDTO dto,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto);
        return ResponseEntity.ok("반차 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/sick")
    @Operation(summary = "병가 신청", description = "사원 병가를 신청합니다. 파일 업로드는 선택적입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "병가 신청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<String> vacationSick(
            @Parameter(description = "병가 신청 데이터 (JSON)", required = true)
            @RequestPart("jsonData") SickFormDTO dto,
            @Parameter(description = "병가 관련 파일 업로드 (선택적)", required = false)
            @RequestPart(value = "sickFileUpload", required = false) MultipartFile[] files,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        log.info("SickFormDTO ={}, fileUpload ={}", dto, files);
        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto, files != null ? files : new MultipartFile[0]);
        return ResponseEntity.ok("병가 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/other")
    @Operation(summary = "기타 휴가 신청", description = "사원 기타 휴가를 신청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기타 휴가 신청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<String> vacationOther(
            @Parameter(description = "기타 휴가 신청 데이터", required = true) @RequestPart("jsonData") OtherFormDTO dto,
            @Parameter(description = "기타 휴가 관련 파일 업로드 (선택적)") @RequestPart(value = "otherFileUpload", required = false) MultipartFile[] files,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        dto.setEmployeeId(sessionEmployeeDTO.getEmployeeId());
        vacationService.save(dto, files != null ? files : new MultipartFile[0]);
        return ResponseEntity.ok("기타 휴가 신청이 성공적으로 처리되었습니다.");
    }

    @PostMapping("/team/approval")
    @Operation(summary = "휴가 승인 반려", description = "팀 휴가 요청을 승인하거나 반려합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "휴가 승인 반려가 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<Void> vacationTeamApproval(
            @Parameter(description = "휴가 승인 요청 데이터", required = true) @RequestBody ApprovalRequestDTO approvalRequest,
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {

        vacationService.approvalVacation(approvalRequest.getCalendarId(), approvalRequest.getStatus(), sessionEmployeeDTO.getEmployeeId());
        return ResponseEntity.ok().build();
    }
}
