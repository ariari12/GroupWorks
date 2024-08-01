package kr.co.groupworks.materialflow.control;

import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public class MaterialFlowManagerController {
    MaterialService materialService;

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

    /* 발주서 / 수주보고서 양식 */
    @GetMapping(value = "/new-order")
    public String newOrder(Model model) {
        log.info("new-order");

        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");
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
    
    /* BOM 작성 */
    @GetMapping(value = "/bom/request")
    public String createBom(Model model) {
        log.info("bom/request");
        model.addAttribute(ATTR_TITLE, "수주/발주 기록");
        model.addAttribute(ATTR_SUB_TITLE, "수주/발주");
        return "materialflow/bomForm";
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

}
