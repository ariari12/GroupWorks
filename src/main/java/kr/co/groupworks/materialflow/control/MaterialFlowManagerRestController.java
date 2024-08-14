package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import kr.co.groupworks.materialflow.dto.*;
import kr.co.groupworks.materialflow.entity.OrderClassification;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/send-complete/{bomId}/{stat}")
    public ResponseEntity<Object> sendComplete(@PathVariable("bomId") Long bomId, @PathVariable("stat") int stat) {
        if (bomId == null || stat < 1 || 2 < stat) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialService.orderCompleteCheck(bomId, stat));
    }

    private final AES256TextEncryptor textEncryptor;
    private final DefaultMessageService messageService;
    @Value("${test.phone}")
    private String testPhone;
    @Value("${test.receive.phone}")
    private String receivePhone;

    @PostMapping("/send-sms/{bomId}")
    /* 메시지 발송 : 발주, 수주 품목이 모두 완료 되었을 때 담당자 연락처로 SMS 전송 */
    private MultipleDetailMessageSentResponse sendSms(@PathVariable("bomId") Long bomId) {
        Map<String, Object> bomSMS = materialService.getBomSMS(bomId);
        if(!(boolean)bomSMS.get("result")) log.info("error: {}", bomSMS.get("message").toString());

        OrderDTO o = (OrderDTO) bomSMS.get("order");
        BomDTO b = o.getBomList().stream().filter(bom -> bom.getId() == bomId)
                .reduce((bomDTO, bomDTO2) -> bomDTO).orElse(null);

        log.info(b.toString());

        EmployeeDTO e = o.getEmployee();
        ManagerDTO m = o.getManager();
        List<Message> messageList = new ArrayList<>();

        String testSendPhone = textEncryptor.decrypt(testPhone);
        String testReceivePhone = textEncryptor.decrypt(receivePhone);

        /* 수주일 경우 수주 담당자 : 사원 */
        if(OrderClassification.getClassification(o.getClassification()) == OrderClassification.RECEIVE) {
            // 수주 담당자에게 발신
            Message message = new Message();
            // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
            message.setFrom(testSendPhone);  // Cool SMS 등록된 발신번호만 사용가능
            message.setTo(testReceivePhone);  // e.getPhone().replaceAll("-", "")
            message.setText("수주담당자: " + e.getName() + ",\n발주담당자: " + m.getPhone()
                    + ",\n품목코드: " + b.getItemCode() + " 수주가 완료되었습니다.");
            messageList.add(message);

            // 발주 담당자에게 발신
            message = new Message();
            message.setFrom(testSendPhone);
            message.setTo(testReceivePhone);  // m.getPhone().replaceAll("-", "")
            message.setText("수주담당자: " + e.getName() + ",\n발주담당자: " + m.getPhone()
                    + ",\n품목코드: " + b.getItemCode() + " 발주가 완료되었습니다.");
            messageList.add(message);
        }
        /* 발주일 경우 발주 담당자 : 사원 */
        else if(OrderClassification.getClassification(o.getClassification()) == OrderClassification.SEND) {
            // 수주 담당자에게 발신
            Message message = new Message();
            // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
            message.setFrom(testSendPhone);
            message.setTo(testReceivePhone);  // e.getPhone().replaceAll("-", "")
            message.setText("수주담당자: " + e.getName() + ",\n발주담당자: " + m.getPhone()
                    + ",\n품목코드: " + b.getItemCode() + " 수주가 완료되었습니다.");
            messageList.add(message);

            // 발주 담당자에게 발신
            message = new Message();
            message.setFrom(testSendPhone);
            message.setTo(testReceivePhone);  // m.getPhone().replaceAll("-", "")
            message.setText("수주담당자: " + e.getName() + ",\n발주담당자: " + m.getPhone()
                    + ",\n품목코드: " + b.getItemCode() + " 발주가 완료되었습니다.");
            messageList.add(message);
        }

        try {
            return messageService.send(messageList, true);
        } catch (NurigoMessageNotReceivedException exception) {
            log.info("NurigoMessageNotReceivedException error: {}", exception.getFailedMessageList());
            log.info("{}", exception.getMessage());
        } catch (Exception exception) {
            log.info("Exception error: {}", exception.getMessage());
        }
        return null;
    }

    private List<Message> snedMassage(String sendPhone, String receivePhone, String snedManager, String receiveManager, String message) {
        List<Message> messageList = new ArrayList<>();
        // 수주 담당자에게 발신
        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom(sendPhone);
        message.setTo(receivePhone);  // e.getPhone().replaceAll("-", "")
        message.setText("수주담당자: " + e.getName() + ",\n발주담당자: " + m.getPhone()
                + ",\n품목코드: " + b.getItemCode() + " 수주가 완료되었습니다.");
        messageList.add(message);

        // 발주 담당자에게 발신
        message = new Message();
        message.setFrom(testSendPhone);
        message.setTo(testReceivePhone);  // m.getPhone().replaceAll("-", "")
        message.setText("수주담당자: " + e.getName() + ",\n발주담당자: " + m.getPhone()
                + ",\n품목코드: " + b.getItemCode() + " 발주가 완료되었습니다.");
        messageList.add(message);

        return messageList;
    }
}


class NotificationsAndSmsInfo {
    String
}
