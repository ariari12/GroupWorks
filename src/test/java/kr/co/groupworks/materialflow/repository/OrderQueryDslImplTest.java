package kr.co.groupworks.materialflow.repository;

import kr.co.groupworks.materialflow.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@SpringBootTest
class OrderQueryDslImplTest {
    @Autowired
    OrderRepository repository;

    @Test @DisplayName("OrderDeleteCheck Test")
    void testOrderDeleteCheck() {
        List<Order> orderList = repository.findAll();
        orderList.forEach(o -> {
            log.info("id:{}, result: {}", o.getId(),
                    repository.orderDeleteCheck(o.getId()));
        });
    }

    @Test @DisplayName("Mes List VO Test")
    void findAllMesAndOrderCode() {
        repository.findAllMesAndOrderCode().forEach(m -> log.info(m.toString()));
    }

    @Test @DisplayName("Calculate Test")
    void calculate() {
        LocalDate start = LocalDate.of(2024, 8, 11);
        LocalDate end = LocalDate.of(2024, 8, 17);
        log.info("result: {}", repository.calculate(start, end));
    }
}