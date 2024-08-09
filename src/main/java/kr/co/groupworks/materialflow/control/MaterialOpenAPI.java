package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.entity.Business;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/openapi/materialflow")
@RequiredArgsConstructor
public class MaterialOpenAPI {
    /* Material Flow Management OpenAPI */
    private final MaterialOpenApiService materialOpenApiService;

    private final String TAGS = "통합 자재/유통 관리(Material Flow Management) OpenAPI";
    private final String RESPONSE_CODE = "200";


    @Operation(tags = TAGS, summary = "거래처 데이터 제공 API", description = "거래처 등록번호에 따른 거래처 데이터 제공")
    @Parameter(name = "businessId", example = "1", description = "해당 거래처 등록번호에 따른 거래처 데이터 제공 (*생략 시 전체 거래처 데이터 제공, 0:본사)")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = Business.class)))
    @GetMapping("/business")
    public ResponseEntity<Object> business(@RequestParam(required = false) Long businessId) {
        Object result = materialOpenApiService.getBusiness(businessId);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당되는 데이터가 없습니다.")
                : ResponseEntity.ok().body(materialOpenApiService.getBusiness(businessId));
    }

    @Operation(tags = TAGS, summary = "거래처 정보 목록 입력 API", description = "거래처 데이터 입력 시 거래처 목록 추가")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = Boolean.class)))
    @PostMapping(value = "/business")
    public ResponseEntity<Object> addBusiness(@RequestBody List<BusinessDTO> businessList) {
        return businessList.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("[businessList]가 비어있습니다.")
                : ResponseEntity.ok().body(materialOpenApiService.setBusinessList(businessList.stream().map(BusinessDTO::dtoToEntity).toList()));
    }

    @Operation(tags = TAGS, summary = "거래처 담당자 정보 제공 API", description = "거래처 담당자 등록번호 혹은 거래 업체에 따른 거래처 담당자 데이터 제공  (* businessId 생략 시 거래처 담당자 등록번호에 따른 데이터 제공, 파라미터가 없을 시 전체 거래처 담당자 데이터 제공)")
    @Parameter(name = "businessId", example = "1", description = "해당 거래처 등록번호에 따른 거래처 담당자 데이터 제공")
    @Parameter(name = "managerId", example = "1", description = "해당 거래처 담당자 등록번호에 따른 거래처 담당자 데이터 제공 (* managerId로 검색할 시 businessId 필요 없음)")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = ManagerDTO.class)))
    @GetMapping("/business-manager")
    public ResponseEntity<Object> getBusinessManager(@RequestParam(required = false) Long businessId, @RequestParam(required = false) Long managerId) {
        if(businessId != null) return ResponseEntity.ok().body(materialOpenApiService.getManagersByBusiness(businessId));
        if(managerId == null) return ResponseEntity.ok().body(materialOpenApiService.getAllManager());
        ManagerDTO b = materialOpenApiService.getManager(managerId);
        return b == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok().body(b);
    }

    @Operation(tags = TAGS, summary = "거래처 담당자 정보 등록 API", description = "거래처 담당자 데이터 등록")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = Boolean.class)))
    @PostMapping("/business-manager")
    public ResponseEntity<Object> getBusinessManager(@RequestBody(required = false) List<ManagerDTO> managerList) {
        if(managerList.size() > 1) {
            materialOpenApiService.setManagers(managerList);
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

}
