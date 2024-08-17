package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import kr.co.groupworks.materialflow.dto.*;
import kr.co.groupworks.materialflow.entity.OrderClassification;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import kr.co.groupworks.notification.model.Notification;
import kr.co.groupworks.notification.service.NotificationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Hidden
@RestController
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public abstract class MaterialFlowManagerRestController {
    /* MaterialFlowManagement RestAPI */
    private final MaterialService materialService;
    private final MaterialOpenApiService materialOpenApiService;
    private final NotificationService notificationService;

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

    /* 발주/수주 완료 유효성검사 */
    @GetMapping("/send-complete/{bomId}/{stat}")
    public ResponseEntity<Object> sendComplete(@PathVariable("bomId") Long bomId, @PathVariable("stat") int stat) {
        if (bomId == null || stat < 1 || 2 < stat) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok().body(materialService.orderCompleteCheck(bomId, stat));
    }

    final AES256TextEncryptor textEncryptor;
    final DefaultMessageService messageService;
    @Value("${test.phone}")
    private String testPhone;
    @Value("${test.receive.phone}")
    private String receivePhone;

    /* 메시지 발송 : 발주, 수주 품목이 모두 완료 되었을 때 담당자 연락처로 SMS 전송 */
    @PostMapping("/send-sms/{bomId}")
    private MultipleDetailMessageSentResponse sendSms(@PathVariable("bomId") Long bomId) throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        Map<String, Object> bomSMS = materialService.getBomSMS(bomId);
        if(!(boolean)bomSMS.get("result")) {
            log.info("error: {}", bomSMS.get("message").toString());
            return null;
        }

        OrderDTO o = (OrderDTO) bomSMS.get("order");
        BomDTO b = o.getBomList().stream().filter(bom -> bom.getId() == bomId)
                .reduce((bomDTO, bomDTO2) -> bomDTO).orElse(null);

        /* SMS message Send SetReady */
        NotificationAndSmsServiceFactory nsFactory = new NotificationAndSmsServiceFactory();
        nsFactory.sendPhone = textEncryptor.decrypt(testPhone);
        nsFactory.receivePhone = textEncryptor.decrypt(receivePhone);

        EmployeeDTO e = o.getEmployee();
        ManagerDTO m = o.getManager();
        /* 수주일 경우 수주 담당자 : 사원 */
        if(OrderClassification.getClassification(o.getClassification()) == OrderClassification.RECEIVE) {
            String message = "수주담당자: " + e.getName() + ",\n발주담당자: " + m.getName() + ",\n품목코드: " + b.getItemCode() + " 수주가 완료되었습니다.";
            // 수주 담당자에게 발신
            // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다. Cool SMS 등록된 발신번호만 사용가능
            // nsFactory.receivePhone = e.getPhone().replaceAll("-", "")
            nsFactory.smsSetup(message);
            String title = "품목코드: " + b.getItemCode() + " 수주가 완료되었습니다.";
            nsFactory.notifySetup(notificationService, e, title, message);

            message = "수주담당자: " + e.getName() + ",\n발주담당자: " + m.getName() + ",\n품목코드: " + b.getItemCode() + " 발주가 완료되었습니다.";
            // 발주 담당자에게 발신
            // nsFactory.receivePhone = m.getPhone().replaceAll("-", "")
            nsFactory.smsSetup(message);
        }
        /* 발주일 경우 발주 담당자 : 사원 */
        else if(OrderClassification.getClassification(o.getClassification()) == OrderClassification.SEND) {
            String message = "수주담당자: " + m.getName() + ",\n발주담당자: " + e.getName() + ",\n품목코드: " + b.getItemCode() + " 수주가 완료되었습니다.";
            // 수주 담당자에게 발신
            // nsFactory.receivePhone = e.getPhone().replaceAll("-", "")
            nsFactory.smsSetup(message);

            message = "수주담당자: " + m.getName() + ",\n발주담당자: " + e.getName() + ",\n품목코드: " + b.getItemCode() + " 발주가 완료되었습니다.";
            // 발주 담당자에게 발신
            // nsFactory.receivePhone = m.getPhone().replaceAll("-", "")
            nsFactory.smsSetup(message);
            String title = "품목코드: " + b.getItemCode() + " 발주가 완료되었습니다.";
            nsFactory.notifySetup(notificationService, e, title, message);
        }
        return messageService.send(nsFactory.getMessageList(), true);
    }
}


class NotificationAndSmsServiceFactory {
    String sendPhone;
    String receivePhone;

    @Getter
    private final List<Message> messageList = new ArrayList<>();

    public void smsSetup(String content) {
        Message message = new Message();
        message.setFrom(sendPhone);
        message.setTo(receivePhone);
        message.setText(content);
        messageList.add(message);
    }

    public void smsSetup(String sendPhone, String receivePhone, String content) {
        Message message = new Message();
        message.setFrom(sendPhone);
        message.setTo(receivePhone);
        message.setText(content);
        messageList.add(message);
    }

    public void notifySetup(NotificationService service, EmployeeDTO e, String title, String content) {
        service.sendNotificationOne(Notification.builder()
                .title(title)
                .contents(content)
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .url("/materialflow/bom")
                .receiverId(e.getId())
                .senderId(e.getId())
                .senderName(e.getName())
                .read(false)
                .build());
    }
}
