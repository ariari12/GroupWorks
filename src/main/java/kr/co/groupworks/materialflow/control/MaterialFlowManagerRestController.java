package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.dto.MaterialStatusUpdateDTO;
import kr.co.groupworks.materialflow.dto.OrderDTO;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
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
//        log.info("request, business: {}", business);
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
//        log.info("request, OrderDTO: {}", orderDTO);
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

    @PutMapping("/material")
    public ResponseEntity<Object> requestMaterialUpdate(@Valid @RequestBody MaterialStatusUpdateDTO itemDTO, BindingResult bindingResult) {
        if(itemDTO == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        log.info(itemDTO.toString());
        if(bindingResult.hasErrors()) {
            // 검증 오류 메시지를 수집하여 반환
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok().body(materialService.updateItems(itemDTO));
    }


    /* 발주서/수주서 작성 취소 (단, 발주서/수주서 담당자 기록이 없어야 함) */
    @DeleteMapping("/order-detail/{orderId}")
    public ResponseEntity<Object> requestOrderDelete(@PathVariable Long orderId) {
        if(orderId == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialService.deleteOrder(orderId));
    }

    /* 거래처 담당자 정보 삭제 (단, 발주서/수주서 담당자 기록이 없어야 함) */
    @DeleteMapping("/manager-select/{managerId}")
    public ResponseEntity<Object> requestManagerDelete(@PathVariable Long managerId) {
        if(managerId == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialService.deleteManager(managerId));
    }

    /* 거래처 정보 삭제 (단, 거래처 참조 데이터가 없어야 함) */
    @DeleteMapping("/business-select/{businessId}")
    public ResponseEntity<Object> requestBusinessDelete(@PathVariable Long businessId) {
        if(businessId == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialService.deleteBusiness(businessId));
    }


    private final AES256TextEncryptor textEncryptor;
    private final DefaultMessageService messageService;
    @Value("${test.phone}")
    private String testPhone;

    /* 메시지 발송 : 발주, 수주 품목이 모두 완료 되었을 때 담당자 연락처로 SMS 전송 */
    @PostMapping("/send-sms")
    public SingleMessageSentResponse sendOne() {
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(textEncryptor.decrypt(testPhone));
        message.setTo(textEncryptor.decrypt(testPhone));
        message.setText("test 중 입니다.");
        return messageService.sendOne(new SingleMessageSendingRequest(message));
    }

}
