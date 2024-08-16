//package kr.co.groupworks.materialflow.repository;
//
//import kr.co.groupworks.materialflow.entity.Order;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@Slf4j
//@SpringBootTest
//class OrderQueryDslImplTest {
//    @Autowired
//    private OrderRepository repository;
//
//    @Test @DisplayName("OrderDeleteCheck Test")
//    public void testOrderDeleteCheck() {
//        List<Order> orderList = repository.findAll();
//        orderList.forEach(o -> {
//            log.info("id:{}, result: {}", o.getId(),
//                    repository.orderDeleteCheck(o.getId()));
//        });
//    }
//}