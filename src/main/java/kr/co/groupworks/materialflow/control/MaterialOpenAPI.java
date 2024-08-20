package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kr.co.groupworks.materialflow.dto.*;
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


    @Operation(tags = TAGS, summary = "거래처 정보 목록 입력 API", description = "거래처 데이터 입력 시 거래처 목록 추가")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = Boolean.class)))
    @PostMapping(value = "/business")
    public ResponseEntity<Object> addBusiness(@RequestBody List<BusinessDTO> businessList) {
        return businessList.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("[businessList]가 비어있습니다.")
                : ResponseEntity.ok().body(materialOpenApiService.setBusinessList(businessList.stream().map(BusinessDTO::dtoToEntity).toList()));
    }

    @Operation(tags = TAGS, summary = "거래처 데이터 제공 API", description = "거래처 등록번호에 따른 거래처 데이터 제공")
    @Parameter(name = "businessId", example = "1", description = "해당 거래처 등록번호에 따른 거래처 데이터 제공 (*생략 시 전체 거래처 데이터 제공, 1:본사)")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = Business.class)))
    @GetMapping(value = "/business")
    public ResponseEntity<Object> business(@RequestParam(required = false) Long businessId) {
        Object result = materialOpenApiService.getBusiness(businessId);
        return result == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당되는 데이터가 없습니다.")
                : ResponseEntity.ok().body(materialOpenApiService.getBusiness(businessId));
    }

    @Operation(tags = TAGS, summary = "거래처 담당자 정보 등록 API", description = "거래처 담당자 데이터 등록")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = Boolean.class)))
    @PostMapping(value = "/business-manager")
    public ResponseEntity<Object> getBusinessManager(@RequestBody(required = false) List<ManagerDTO> managerList) {
        if(managerList.size() > 1) {
            materialOpenApiService.setManagers(managerList);
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @Operation(tags = TAGS, summary = "거래처 담당자 정보 제공 API", description = "거래처 담당자 등록번호 혹은 거래 업체에 따른 거래처 담당자 데이터 제공  (* businessId 생략 시 거래처 담당자 등록번호에 따른 데이터 제공, 파라미터가 없을 시 전체 거래처 담당자 데이터 제공)")
    @Parameter(name = "businessId", example = "1", description = "해당 거래처 등록번호에 따른 거래처 담당자 데이터 제공")
    @Parameter(name = "managerId", example = "1", description = "해당 거래처 담당자 등록번호에 따른 거래처 담당자 데이터 제공 (* managerId로 검색할 시 businessId 필요 없음)")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = ManagerDTO.class)))
    @GetMapping(value = "/business-manager")
    public ResponseEntity<Object> getBusinessManager(@RequestParam(required = false) Long businessId, @RequestParam(required = false) Long managerId) {
        if(businessId != null) return ResponseEntity.ok().body(materialOpenApiService.getManagersByBusiness(businessId));
        if(managerId == null) return ResponseEntity.ok().body(materialOpenApiService.getAllManager());
        ManagerDTO b = materialOpenApiService.getManager(managerId);
        return b == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok().body(b);
    }

    @Operation(tags = TAGS, summary = "발주/수주 주문기록 정보 제공 API", description = "발주/수주 주문기록 정보 제공 API 모든 Parameter 가 포함된 데이터 반환")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @Parameter(name = "orderCode", description = "발주/수주서 번호로 주문기록 조회", example = "2020120317-0A1561733-A1V3")
    @Parameter(name = "itemCode", description = "품목 코드로 주문기록 조회", example = "0A1561733-A1V3-2")
    @Parameter(name = "itemName", description = "품목 명으로 주문기록 조회", example = "iPhone 16 다이아 케이스(색상 블루 계열)")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestParam(required = false) String orderCode, @RequestParam(required = false) String itemCode, @RequestParam(required = false) String itemName) {
        List<OrderDTO> os = materialOpenApiService.getOrderList(orderCode, itemCode, itemName);
        if(os.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(os);
        return ResponseEntity.ok().body(os);
    }

    @Operation(tags = TAGS, summary = "모든 자재관리 현황 정보 제공 API", description = "모든 BOM 자재/재고/유통 현황 정보제공 API")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = BomDTO.class)))
    @GetMapping("/bom")
    public ResponseEntity<Object> getBom() {
        return ResponseEntity.ok().body(materialOpenApiService.getBomList());
    }

    @Operation(tags = TAGS, summary = "MES연동 생산기록 단일 등록 API", description = "생산기록 단일 데이터 등록 API")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = MesOpenApiDTO.class)))
    @PostMapping(value = "/mes-one")
    public ResponseEntity<Object> setMesOne(@RequestBody(required = false) MesOpenApiDTO mesDTO) {
        if(mesDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialOpenApiService.setMes(mesDTO));
    }

    @Operation(tags = TAGS, summary = "MES연동 생산기록 복수 등록 API", description = "생산기록 리스트(여러 개) 데이터 등록 API")
    @ApiResponse(responseCode = RESPONSE_CODE, content = @Content(schema = @Schema(implementation = MesOpenApiDTO.class)))
    @PostMapping(value = "/mes-list")
    public ResponseEntity<Object> setMesList(@RequestBody(required = false) List<MesOpenApiDTO> mesList) {
        if(mesList.size() > 1) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialOpenApiService.setMesList(mesList));
    }

}
