package com.example.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class JasyptConfigTest {

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    StringEncryptor encryptor;

    @Test
    void test() {
        String a = "jasypt_testkey";
        String enc1 = encryptor.encrypt(a);
        log.info("en1 : {}",enc1);

        String dec1 = encryptor.decrypt(enc1);
        log.info("dec1 : {}",dec1);

        String mongourl = "mongodb+srv://admin:내암호@cluster0.1p17esk.mongodb.net/sample_airbnb";

        String enc_mongourl = encryptor.encrypt(mongourl);
        log.info("enc_mongourl = {}",enc_mongourl);

        String dec_mongourl = encryptor.decrypt(enc_mongourl);
        log.info("dec_mongourl = {}",dec_mongourl);


    }

}