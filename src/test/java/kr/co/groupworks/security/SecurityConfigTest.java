//package kr.co.groupworks.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Slf4j
//@SpringBootTest
//class SecurityConfigTest {
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Test @DisplayName("BCryptPasswordEncoder Test")
//    public void BCryptPasswordEncoderTest() {
//        final String password = "1111";
//        final String encode = bCryptPasswordEncoder.encode(password);
//
//        log.info("pw: {}, encode:{}", password, encode);
//    }
//
//}