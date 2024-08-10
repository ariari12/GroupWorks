//package kr.co.groupworks.materialflow.service;
//
//import kr.co.groupworks.materialflow.entity.Business;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@SpringBootTest
//@Transactional
//class MaterialServiceTest {
//    @Autowired
//    MaterialServiceImpl service;
//
//    @Autowired
//    MaterialOpenApiServiceImpl materialOpenApiService;
//
//    private final List<Business> businesses = new ArrayList<>();
//
//    @BeforeEach @DisplayName("Given All")
//    void setUp() {
//        businesses.add(Business.builder()
//                .businessNumber("123-45-67890")
//                .businessName("◎◎제약™")
//                .ceo("홍길동")
//                .ceoTel("010-1234-1325")
//                .type("제조업")
//                .item("유제품")
//                .address("경기도 이천시 이천동 2000번지 2000-1")
//                .fax("031-123-1325")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("124-45-67891")
//                .businessName("◇◇식품")
//                .ceo("김영희")
//                .ceoTel("010-5678-1325")
//                .type("식품업")
//                .item("음료수")
//                .address("서울특별시 강남구 테헤란로 123")
//                .fax("02-567-1325")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("125-45-67892")
//                .businessName("☆☆유통")
//                .ceo("이철수")
//                .ceoTel("010-8765-4321")
//                .type("유통업")
//                .item("가전제품")
//                .address("부산광역시 해운대구 우동 1234")
//                .fax("051-876-4321")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("126-45-67893")
//                .businessName("▲▲무역")
//                .ceo("박영수")
//                .ceoTel("010-4567-7890")
//                .type("무역업")
//                .item("자동차 부품")
//                .address("대구광역시 달서구 상인동 987")
//                .fax("053-456-7890")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("127-45-67894")
//                .businessName("♤♤화학")
//                .ceo("최지우")
//                .ceoTel("010-2345-6789")
//                .type("화학업")
//                .item("화학물질")
//                .address("울산광역시 남구 삼산동 456")
//                .fax("052-234-6789")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("128-45-67895")
//                .businessName("◆◆농산")
//                .ceo("김민수")
//                .ceoTel("010-3456-7891")
//                .type("농업")
//                .item("농산물")
//                .address("전라북도 전주시 완산구 서노송동 789")
//                .fax("063-345-7891")
//                .build());
//        businesses.add(Business.builder()
//                .id(7L)
//                .businessNumber("129-45-67896")
//                .businessName("◇◇기계")
//                .ceo("이성민")
//                .ceoTel("010-4567-8912")
//                .type("기계업")
//                .item("기계부품")
//                .address("경상남도 창원시 마산합포구 중앙동 123")
//                .fax("055-456-8912")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("130-45-67897")
//                .businessName("○○전자")
//                .ceo("박현준")
//                .ceoTel("010-5678-9123")
//                .type("전자업")
//                .item("전자제품")
//                .address("경기도 수원시 팔달구 매산로 456")
//                .fax("031-567-9123")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("131-45-67898")
//                .businessName("△△의료")
//                .ceo("최예진")
//                .ceoTel("010-6789-1234")
//                .type("의료업")
//                .item("의료기기")
//                .address("서울특별시 마포구 서교동 789")
//                .fax("02-678-1234")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("132-45-67899")
//                .businessName("▣▣섬유")
//                .ceo("홍석현")
//                .ceoTel("010-7891-2345")
//                .type("섬유업")
//                .item("섬유제품")
//                .address("대전광역시 중구 은행동 123")
//                .fax("042-789-2345")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("123-45-67890")
//                .businessName("삼성전자")
//                .ceo("이재용")
//                .ceoTel("010-1234-5678")
//                .type("제조업")
//                .item("전자제품")
//                .address("서울특별시 강남구 서초동 123-45")
//                .fax("02-123-4567")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("124-45-67891")
//                .businessName("LG화학")
//                .ceo("신학철")
//                .ceoTel("010-2345-6789")
//                .type("화학업")
//                .item("화학물질")
//                .address("서울특별시 영등포구 여의도동 234-56")
//                .fax("02-234-5678")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("125-45-67892")
//                .businessName("현대자동차")
//                .ceo("정의선")
//                .ceoTel("010-3456-7890")
//                .type("제조업")
//                .item("자동차")
//                .address("서울특별시 서초구 양재동 345-67")
//                .fax("02-345-6789")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("126-45-67893")
//                .businessName("네이버")
//                .ceo("최수연")
//                .ceoTel("010-4567-8901")
//                .type("서비스업")
//                .item("인터넷 서비스")
//                .address("경기도 성남시 분당구 불정로 6")
//                .fax("031-456-7890")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("127-45-67894")
//                .businessName("카카오")
//                .ceo("홍은택")
//                .ceoTel("010-5678-9012")
//                .type("정보통신업")
//                .item("모바일 서비스")
//                .address("경기도 성남시 분당구 대왕판교로 645번길 16")
//                .fax("031-567-8901")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("128-45-67895")
//                .businessName("아모레퍼시픽")
//                .ceo("서경배")
//                .ceoTel("010-6789-0123")
//                .type("제조업")
//                .item("화장품")
//                .address("서울특별시 용산구 한강대로 100")
//                .fax("02-678-9012")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("129-45-67896")
//                .businessName("하이트진로")
//                .ceo("김인규")
//                .ceoTel("010-7890-1234")
//                .type("제조업")
//                .item("음료")
//                .address("서울특별시 강남구 테헤란로 152")
//                .fax("02-789-0123")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("130-45-67897")
//                .businessName("SK하이닉스")
//                .ceo("이석희")
//                .ceoTel("010-8901-2345")
//                .type("제조업")
//                .item("반도체")
//                .address("경기도 이천시 부발읍 경충대로 2091")
//                .fax("031-890-1234")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("131-45-67898")
//                .businessName("CJ제일제당")
//                .ceo("강신호")
//                .ceoTel("010-9012-3456")
//                .type("제조업")
//                .item("식품")
//                .address("서울특별시 중구 동호로 330")
//                .fax("02-901-2345")
//                .build());
//        businesses.add(Business.builder()
//                .businessNumber("132-45-67899")
//                .businessName("대한항공")
//                .ceo("우기홍")
//                .ceoTel("010-0123-4567")
//                .type("운송업")
//                .item("항공 서비스")
//                .address("서울특별시 강서구 하늘길 77")
//                .fax("02-012-3456")
//                .build());
//    }
//
//    @Test @DisplayName("setBusinessList Test")
//    public void testSetBusinessList() {
//        boolean result = materialOpenApiService.setBusinessList(businesses);
//        log.info("result: {}", result);
//        Assertions.assertTrue(result);
//    }
//}