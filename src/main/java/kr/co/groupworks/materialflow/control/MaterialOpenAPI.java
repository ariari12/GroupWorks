package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.co.groupworks.materialflow.entity.Business;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/materialflow")
@RequiredArgsConstructor
public class MaterialOpenAPI {
    /* Material Flow Management OpenAPI */
    private final MaterialOpenApiService materialOpenApiService;

    @Operation(tags = "통합 자재/유통 관리(Material Flow Management) OpenAPI", summary = "거래처 데이터 제공 API", description = "거래처 등록번호에 따른 거래처 데이터 제공 (*생략 시 전체 거래처 데이터 제공)")
    @Parameter(name = "businessId", example = "거래처 등록 번호", required = false, description = "해당 거래처 등록번호에 따른 거래처 데이터 제공 (*생략 시 전체 거래처 데이터 제공)")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Business.class)))
    @GetMapping("/business")
    public ResponseEntity<Object> business(@RequestParam(required = false) Long businessId) {
        Object result = materialOpenApiService.getBusiness(businessId);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당되는 데이터가 없습니다.")
                : ResponseEntity.ok().body(materialOpenApiService.getBusiness(businessId));
    }

    @Operation(tags = "통합 자재/유통 관리(Material Flow Management) OpenAPI", summary = "거래처 정보 목록 입력 API", description = "거래처 데이터 입력 시 거래처 목록 추가")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @PostMapping(value = "/business")
    public ResponseEntity<Object> addBusiness(@RequestBody List<BusinessDTO> businessList) {
        return businessList.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("[businessList]가 비어있습니다.")
                : ResponseEntity.ok().body(materialOpenApiService.setBusinessList(businessList.stream().map(BusinessDTO::dtoToEntity).toList()));
    }

}
