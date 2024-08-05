package kr.co.groupworks.materialflow.control;

import kr.co.groupworks.materialflow.entity.Business;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
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
        log.info("order-record");
        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");
        return "materialflow/orderRecord";
    }

    /* 발주서 / 수주서 양식 */
    @GetMapping(value = "/new-order/{form}")
    public String newOrder(@PathVariable("form") Integer f, Model model) {
        log.info("new-order");
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
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
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
        log.info("new-business");
        String title = "거래처 등록", subTitle = "거래처 등록 양식";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        return "materialflow/window/newBusiness";
    }
    /* 거래처 선택 창 */
    @GetMapping(value = "/business-select")
    public String businessSelect(Model model) {
        log.info("business-select");
        List<Business> businessList = (List<Business>)materialOpenApiService.getBusiness(null);
        String title = "거래처 선택", subTitle = "거래처 목록";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
        log.info("business-select, size: " + businessList.size());
        model.addAttribute("businessList", businessList);
        return "materialflow/window/businessSelect";
    }
    /* 발주/수주 담당자 선택 창 */
    @GetMapping(value = "/manager-select")
    public String managerSelect(Model model) {
        log.info("manager-select");
//        List<Employee> managerList = ;
        String title = "담당자 선택", subTitle = "사원 목록";
        model.addAttribute(ATTR_TITLE, title);
        model.addAttribute(ATTR_SUB_TITLE, subTitle);
//        log.info("manager-select, size: " + managerList.size());
//        model.addAttribute("managerList", managerList);
        return "materialflow/window/managerSelect";
    }

}
