package kr.co.groupworks.materialflow.control;

import kr.co.groupworks.materialflow.dto.OrderDTO;
import kr.co.groupworks.materialflow.service.MaterialOpenApiService;
import kr.co.groupworks.materialflow.service.MaterialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@Hidden
@RestController
@RequestMapping(value = "/materialflow")
@RequiredArgsConstructor
public class MaterialFlowManagerRestController {
    /* MaterialFlowManagement RestAPI */
    MaterialService materialService;
    MaterialOpenApiService materialOpenApiService;

    @GetMapping("/request")
    public ResponseEntity<Object> request(@RequestBody OrderDTO order) {
        log.info("request, order: {}", order);
        return ResponseEntity.ok().body(null);
    }

}
