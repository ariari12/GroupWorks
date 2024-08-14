package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.EmployeeDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
import kr.co.groupworks.materialflow.dto.OrderDTO;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Slf4j
@Hidden
@Controller
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public class MaterialFlowManagerController {
    private final MaterialService materialService;
    private final MaterialOpenApiService materialOpenApiService;

    public static final String ATTR_TITLE = "title", ATTR_SUB_TITLE = "subtitle";
    public static final String RECEIVE_LIST = "receiveList", SEND_LIST = "sendList";

    /* Material Flow Management API */
    /* 발주/수주 기록 */
    @GetMapping(value = "/order-record")
    public String orderRecord(Model model) {
        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");
        materialService.getOrders().forEach(model::addAttribute);
        return "materialflow/orderRecord";
    }

    /* 발주서 / 수주서 양식 */
    @GetMapping(value = "/new-order/{form}")
    public String newOrder(@PathVariable("form") Integer f, Model model) {
        if(f == null) return "materialflow/orderRecord";
        String title = "", subTitle = "";
        if(f == 1) {
            title = "발주서";
            subTitle = "발주서 양식";
        } else if(f == 2){
            title = "수주서";
            subTitle = "수주서 양식";
        }
        model.addAttribute("division", f);
        model.addAttribute("orderCode", getOrderCode(f));
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        model.addAttribute("office", materialOpenApiService.getBusiness(1L));

        return "materialflow/newOrder";
    }

    /* 발주서 / 수주서 상세 기록 보기 */
    @GetMapping(value = "/order-detail/{orderId}/{classification}")
    public String orderDetail(@PathVariable("orderId") Long orderId, @PathVariable("classification") Integer classification, Model model) {
        String title = "";
        if(classification == 1) {
            title = "발주서";
        } else if(classification == 2) {
            title = "수주서";
        }
        if(orderId == null || orderId == 0) return "/error/404";
        OrderDTO o = materialOpenApiService.getOrder(orderId);
        if(o == null) return "/error/404";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, title);
        model.addAttribute("division", classification);
        model.addAttribute("order", o);
        model.addAttribute("office", materialOpenApiService.getBusiness(1L));
        return "materialflow/orderDetail";
    }

    /* BOM 자재 현황 */
    @GetMapping(value = "/bom")
    public String bom(Model model) {
        String title = "BOM", subTitle = "품목 관리";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        model.addAttribute("bomList", materialService.getBomList());
        return "materialflow/bomRecord";
    }

    /* BOM 자재 품목에 속하는 자재 목록 조회 */
    @GetMapping(value = "/item/{bomId}/{itemCode}/{itemName}")
    public String bom(@PathVariable("bomId") long bomId, @PathVariable("itemCode") String ic, @PathVariable("itemName") String in, Model model) {
        String title = "BOM 자재 현황", subTitle = "자재 관리";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        model.addAttribute("itemCode", ic);
        model.addAttribute("itemName", in);
        model.addAttribute("itemList", materialService.getItemList(bomId));
        return "materialflow/window/materialRecord";
    }

    /* MES 생산 현황 */
    @GetMapping(value = "/mes")
    public String mes(Model model) {
        String title = "생산 현황", subTitle = "생산 재고 현황";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        return "materialflow/mes";
    }

    /* 매출액 산출 */
    @GetMapping(value = "/take-summation")
    public String takeSummation(Model model) {
        String title = "매출액 산출", subTitle = "영업 손익 계산";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        return "materialflow/takeSummation";
    }

    /* 신규 거래처 등록 창 */
    @GetMapping(value = "/new-business")
    public String newBusiness(Model model) {
        String title = "거래처 등록", subTitle = "거래처 등록 양식";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        return "materialflow/window/newBusiness";
    }

    /* 거래처 선택 창 */
    @GetMapping(value = "/business-select")
    public String businessSelect(Model model) {
        String title = "거래처 선택";
        List<BusinessDTO> businessList = (List<BusinessDTO>)materialOpenApiService.getBusiness(null);
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, title);
        model.addAttribute("businessList", businessList);
        return "materialflow/window/businessSelect";
    }

    /* 발주/수주 거래처 담당자 선택 창 */
    @GetMapping(value = "/manager-select/{businessId}")
    public String managerSelect(@PathVariable("businessId") Long businessId, Model model) {
        String title = "거래처 담당자 선택";
        List<ManagerDTO> managerList = materialOpenApiService.getManagersByBusiness(businessId);

        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, title);
        model.addAttribute("businessId", businessId);
        model.addAttribute("managerList", managerList);
        return "materialflow/window/managerSelect";
    }

    /* 발주/수주 담당자 사원 선택 창 */
    @GetMapping(value = "/employee-select")
    public String employeeSelect(Model model) {
        String title = "담당자 사원 선택";
        List<EmployeeDTO> managerList = materialService.getAllEmployee();
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, title);
        model.addAttribute("employeeList", managerList);
        return "materialflow/window/employeeSelect";
    }

    /* 주문 고유번호 생성 */
    private String getOrderCode(Integer f) {
        LocalDateTime now = LocalDateTime.now();
        String code = now.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        /*
         * 고유 번호: (발주:0A, 수주 0B) + 작성시각 (ms),분,초
         * 랜덤코드: 정수와 영문 대문자 포함 랜덤3자리
         * 주문코드: 고유 번호 + "-" + 랜덤코드 + "품목개수"
         * 고유 주문 번호: 작성날짜 + "-" + 주문코드
         * example: 20120618-0A13733178-3AB7
         */
        return code + "-" + (f == 1 ? "0A" : "0B")
                + now.format(DateTimeFormatter.ofPattern("SSSmmss"))  + "-" + getRandomCode();
    }

    private String getRandomCode() {
        Random random = new Random();

        // 3자리 랜덤 문자열 생성
        StringBuilder randomString = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            if (random.nextBoolean()) {
                // 숫자(0-9) 선택
                char randomDigit = (char) (random.nextInt(10) + '0');
                randomString.append(randomDigit);
            } else {
                // 대문자(A-Z) 선택
                char randomChar = (char) (random.nextInt(26) + 'A');
                randomString.append(randomChar);
            }
        }
        return randomString.toString();
    }

}
