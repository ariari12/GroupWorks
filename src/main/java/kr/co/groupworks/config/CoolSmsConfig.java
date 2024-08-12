package kr.co.groupworks.config;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class CoolSmsConfig {
    @Value("${coolsms.api.dmain.url}")
    private String smsApiDomain;
    @Value("${coolsms.encryptor.key.file-path}")
    private String keyFilePath;
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
    public AES256TextEncryptor setEncryptor() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(keyFilePath)));
        log.info("key property: {}", content);

        encryptor = new AES256TextEncryptor();
        encryptor.setPassword(content);
        return encryptor;
    }
}
