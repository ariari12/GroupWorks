package kr.co.groupworks.workflow.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.workflow.dto.dto.ExampleStatusDTO;
import kr.co.groupworks.workflow.dto.employee.EmployeeDTO;
import kr.co.groupworks.workflow.service.WorkFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OpenApiController {
    private final WorkFlowService workFlowService;

    @Operation(summary = "전체 사원 데이터", description = "전체 사원 데이터 목록 데이터 반환", tags = "Employee-OpenAPI")
    @ApiResponse(responseCode = "200", description = "전체 사원 데이터 목록 데이터 반환", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class) ) )
    @ApiResponse(responseCode = "404", description = "Url 에러", content = @Content(schema = @Schema(example = "404 Not Found Error")))
    @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(schema = @Schema(example = "500 Server Error")))
    /* 전체 사원정보 */
    @GetMapping(value = "/work-flow/employee")
    public ResponseEntity<Map<String, Object>> employee() {
        List<EmployeeDTO> employeeList = workFlowService.getEmployeeAllDTOList();
//        log.info("WorkFlowRestController - employee ok");

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("result", true);
        response.put("employee", employeeList);

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "결재 통계 데이터",
            description = "로그인한 사원의 사원번와 부서번호를 사용하여 각 종류의 따른 통계데이터 반환, parameter: 반환받을 데이터 종류 Code",
            tags = "Workflow-OpenAPI"
    )
    @Parameter(name = "param",
            description = "Api 반환 데이터 종류 Code: { 1 부서 목록, 부서별 결재 발송/승인/반려 건 수, 2: 올해 연도 전체 결재 발송 건 수," +
                    " 3: 부서 결재 완료 목록, 4: 월 별 결재 발송/승인/반려 건, 5: 누적 결재 발송 건 수 }",
            required = true,
            example = "1~6(Code 번호)"
    )
    @ApiResponse(responseCode = "200",
            description = "요청 code에 따른 세션 사원정보에 해당하는 데이터 목록 데이터 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExampleStatusDTO.class))
    )
    @ApiResponse(responseCode = "404", description = "Url 에러", content = @Content(schema = @Schema(example = "404 Not Found Error")))
    @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(schema = @Schema(example = "500 Server Error")))
    @GetMapping(value = "/work-flow/stat/{param}")
    public ResponseEntity<Object> workStatus(@PathVariable int param, HttpSession session) {
        log.info("WorkFlowRestController - workStatus ok, param: {}", param);

        SessionEmployeeDTO sessionDTO = (SessionEmployeeDTO) session.getAttribute("employee");
        log.info("WorkFlowRestController - workStatus ok, sessionDTO: {}", sessionDTO);

        Object result = workFlowService.getWorkflowStatistics(sessionDTO.getEmployeeId(), sessionDTO.getDepartment().getDepartmentId(), param);
        if (result != null) {
            log.info("WorkFlowRestController - workStatus ok, result: {}", result);
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
