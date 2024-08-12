package kr.co.groupworks.config;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class CoolSmsConfig {
    @Value("${coolsms.api.dmain.url}")
    private String smsApiDomain;
    @Value("${coolsms.encryptor.key.property}")
    private String keyProperty;
    @Value("${coolsms.api-key}")
    private String apiKey;
    @Value("${coolsms.secret-key}")
    private String apiSecretKey;

    private AES256TextEncryptor encryptor;

    @Bean
    public DefaultMessageService encodeApiKeyCreateMessageService() {
        setEncryptor();
        String key = encryptor.decrypt(apiKey);
        String secret = encryptor.decrypt(apiSecretKey);
        return NurigoApp.INSTANCE.initialize(key, secret, smsApiDomain);
    }

    @Bean
    public AES256TextEncryptor setEncryptor(){
        log.info("keyProperty: " + keyProperty);
        encryptor = new AES256TextEncryptor();
        encryptor.setPassword(keyProperty);
        return encryptor;
    }
}
