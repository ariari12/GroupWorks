package kr.co.groupworks.materialflow.control;

import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public class MaterialFlowManagerController {
    MaterialService materialService;

    /* Material Flow Management API */

    /* 발주 기록 */
    @GetMapping(value = "/order-record")
    public String orderRecord() {
        log.info("order-record");

        return "materialflow/orderRecord";
    }

    /* 발주서 양식 */
    @GetMapping(value = "/new-order")
    public String newOrder() {
        log.info("new-order");
        
        return "materialflow/newOrder";
    }

    /* BOM 자재 현황 */
    @GetMapping(value = "/bom")
    public String bom() {
        log.info("bom");
        
        return "materialflow/bom";
    }
    
    /* BOM 작성 */
    @GetMapping(value = "/bom/request")
    public String createBom() {
        log.info("bom/request");

        return "materialflow/bomForm";
    }

    /* MES 생산 현황 */
    @GetMapping(value = "/mes")
    public String mes() {
        log.info("mes");
        
        return "materialflow/mes";
    }

    /* 매출액 산출 */
    @GetMapping(value = "/take-summation")
    public String takeSummation() {
        log.info("take-summation");

        return "materialflow/takeSummation";
    }

}
