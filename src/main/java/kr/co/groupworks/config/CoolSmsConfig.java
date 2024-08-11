package kr.co.groupworks.config;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoolSmsConfig {
    private static final Logger log = LoggerFactory.getLogger(CoolSmsConfig.class);
    @Value("${coolsms.api.dmain.url}")
    private String smsApiDomain;
    @Value("${coolsms.encryptor-key}")
    private String encryptorKey;
    @Value("${coolsms.api-key}")
    private String apiKey;
    @Value("${coolsms.secret-key}")
    private String apiSecretKey;

    private AES256TextEncryptor encryptor;

    @Bean
    public DefaultMessageService encodeApiKeyCreateMessageService() {
        String key = encryptor.decrypt(apiKey);
        String secret = encryptor.decrypt(apiSecretKey);
        return NurigoApp.INSTANCE.initialize(key, secret, smsApiDomain);
    }

    @Bean
    public AES256TextEncryptor setEncryptor() {
        encryptor = new AES256TextEncryptor();
        encryptor.setPassword(encryptorKey);
        return encryptor;
    }
}
