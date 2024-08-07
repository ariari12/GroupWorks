package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.dto.OrderDTO;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@Hidden
@RestController
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public class MaterialFlowManagerRestController {
    /* MaterialFlowManagement RestAPI */
    private final MaterialService materialService;
    private final MaterialOpenApiService materialOpenApiService;

    /* 거래처 정보 등록 */
    @PostMapping("/new-business")
    public ResponseEntity<Object> requestBusiness(@Valid @RequestBody BusinessDTO business, BindingResult bindingResult) {
        log.info("request, business: {}", business);
        if(business == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        if(bindingResult.hasErrors()) {
            // 검증 오류 메시지를 수집하여 반환
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.ok().body(materialService.setBusiness(business));
    }

    /* 거래처 담당자 정보 등록 */
    @PostMapping("/business-manager")
    public ResponseEntity<Object> requestManager(@Valid @RequestBody ManagerDTO manager, @RequestParam Long businessId, BindingResult bindingResult) {
        log.info("request, bId: {}, manager: {}", businessId, manager);
        if(manager == null || businessId == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        if(bindingResult.hasErrors()) {
            // 검증 오류 메시지를 수집하여 반환
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok().body(materialService.setManager(manager, businessId));
    }

    /* 발주서, 수주서 작성 등록 */
    @PostMapping("/new-order")
    public ResponseEntity<Object> requestOrder(@Valid @RequestBody OrderDTO orderDTO, BindingResult bindingResult) {
        log.info("request, OrderDTO: {}", orderDTO);
        if(orderDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        if(bindingResult.hasErrors()) {
            // 검증 오류 메시지를 수집하여 반환
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok().body(materialService.setOrder(orderDTO));
    }

}
