package kr.co.groupworks;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptConfigTest {

    @Test
    void encryptTest(){
        String id="groupworks";
        String password="jtha2401$groupworks";
        String url = "mongodb+srv://kahback99:BRegj0o8YnRvOVcv@groupworks.l6kx7bc.mongodb.net/?retryWrites=true&w=majority&appName=groupworks";
//        System.out.println("jasyptEncrypt(id) = " + jasyptEncrypt(id)); //yKzxhyVq8YuD5nNdKbziyg==
//        System.out.println("jasyptEncrypt(password) = " + jasyptEncrypt(password)); //5CbPCga7Mao9bgxDnu69pg==
        System.out.println("jasyptEncrypt(url) = " + jasyptEncrypt(url));
        System.out.println("jasyptEncrypt(id) = " + jasyptEncrypt(id));
        System.out.println("jasyptEncrypt(password) = " + jasyptEncrypt(password));
    }

    public String jasyptEncrypt(String value){
        String keyword = "helloworld";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(keyword);
        return encryptor.encrypt(value);
    }
}
