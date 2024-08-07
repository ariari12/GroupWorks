package kr.co.groupworks.materialflow.control;

import io.swagger.v3.oas.annotations.Hidden;
import kr.co.groupworks.materialflow.dto.BusinessDTO;
import kr.co.groupworks.materialflow.dto.EmployeeDTO;
import kr.co.groupworks.materialflow.dto.ManagerDTO;
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

@Slf4j
@Hidden
@Controller
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public class MaterialFlowManagerController {
    private final MaterialService materialService;
    private final MaterialOpenApiService materialOpenApiService;

    private String ATTR_TITLE = "title";
    private String ATTR_SUB_TITLE = "subtitle";

    /* Material Flow Management API */
    /* 발주/수주 기록 */
    @GetMapping(value = "/order-record")
    public String orderRecord(Model model) {
        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");
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
        model.addAttribute("office", materialOpenApiService.getBusiness(0L));

        return "materialflow/newOrder";
    }

    /* BOM 자재 현황 */
    @GetMapping(value = "/bom")
    public String bom(Model model) {

        log.info("bom");

        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");

        return "materialflow/bom";
    }

    /* MES 생산 현황 */
    @GetMapping(value = "/mes")
    public String mes(Model model) {

        log.info("mes");

        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");

        return "materialflow/mes";
    }

    /* 매출액 산출 */
    @GetMapping(value = "/take-summation")
    public String takeSummation(Model model) {

        log.info("take-summation");

        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");

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
        String title = "담당자 사원 선택";
        List<ManagerDTO> managerList = materialOpenApiService.getManagersByBusiness(businessId);
        log.info("manager-select, title: {}, size: {}", title, managerList.size());

        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, title);
        model.addAttribute("managerList", managerList);

        return "materialflow/window/managerSelect";
    }
    /* 발주/수주 담당자 사원 선택 창 */
    @GetMapping(value = "/employee-select")
    public String employeeSelect(Model model) {
        String title = "거래처 담당자 선택";
        List<EmployeeDTO> managerList = materialService.getAllEmployee();
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, title);
        model.addAttribute("employeeList", managerList);
        return "materialflow/window/employeeSelect";
    }

    private String getOrderCode(Integer f) {
        LocalDateTime now = LocalDateTime.now();
        String code = now.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        /*
         * 고유 주문 번호: (발주:0A, 수주 0B) + 작성시각(ms)
         * 주문 번호: 작성날짜 + "-" + 고유 주문 번호 + "품목개수"
         * example: 20120618-0A337
         */
        return code + "-" + (f == 1 ? "0A" : "0B")
                + now.format(DateTimeFormatter.ofPattern("SSS")) ;
    }

}
