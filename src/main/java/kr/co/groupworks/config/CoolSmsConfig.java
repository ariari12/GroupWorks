//package kr.co.groupworks.config;
//
//import lombok.extern.slf4j.Slf4j;
//import net.nurigo.sdk.NurigoApp;
//import net.nurigo.sdk.message.service.DefaultMessageService;
//import org.jasypt.util.text.AES256TextEncryptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//@Slf4j
//@Configuration
//public class CoolSmsConfig {
////    @Value("${coolsms.api.dmain.url}")
//    private String smsApiDomain;
////    @Value("${coolsms.api-key}")
//    private String apiKey = "NCS5YXRIXQPF7INV";
////    @Value("${coolsms.secret-key}")
//    private String apiSecretKey = "FPPRUKWMCG6N3S9SNWSNQG05QOX49WVR";
//
//    private AES256TextEncryptor encryptor;
//
//    @Bean
//    public DefaultMessageService encodeApiKeyCreateMessageService() {
//        String key = encryptor.decrypt(apiKey);
//        String secret = encryptor.decrypt(apiSecretKey);
//        log.info("apikey : " + key);
//        log.info("secret : " + apiSecretKey);
//        return NurigoApp.INSTANCE.initialize(key, secret, smsApiDomain);
//    }
//
//    @Bean
//    public AES256TextEncryptor setEncryptor() throws IOException {
//        String content = new String(Files.readAllBytes(Paths.get("D://key.txt")));
//        log.info("key property: {}", content);
//
//        encryptor = new AES256TextEncryptor();
//        encryptor.setPassword(content);
//        return encryptor;
//    }
//}