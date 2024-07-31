package kr.co.groupworks.materialflow.control;

import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = "/material-flow")
@RequiredArgsConstructor
public class MaterialFlowManagerController {
    MaterialService materialService;

    /* MaterialFlowManagement API */

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
    public String status() {
        log.info("status");
        
        return "materialflow/status";
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
