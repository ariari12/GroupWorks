package kr.co.groupworks.materialflow.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

@Slf4j
@SpringBootTest
@Transactional
class MaterialOpenApiServiceImplTest {
    @Autowired
    private MaterialService materialService;

    @Test @DisplayName("Bom Order List FindAll QueryDsl Test")
    void getBomList() {
        StopWatch st = new StopWatch();
        st.start();
        materialService.getBomList().forEach(o -> {
            log.info("Order Id: {}", o.getId());
            log.info("Order Bom List =======");
            o.getBomList().forEach(b -> log.info("  Bom : {}", b));
        });
        st.stop();
        log.info(st.prettyPrint());
    }
}