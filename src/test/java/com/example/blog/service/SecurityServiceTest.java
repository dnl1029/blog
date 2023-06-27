package com.example.blog.service;

import com.example.blog.dto.NewMember;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class SecurityServiceTest {

    @Autowired
    SecurityService securityService;

    @Autowired
    @Qualifier("passwordEncoder")
    PasswordEncoder passwordEncoder;

    @Test
    void test() {
        NewMember newMember = securityService.createUser("admin", "admin123", passwordEncoder);
        log.info("newMember : {}",newMember);

        boolean matches = passwordEncoder.matches("admin123", "$2a$10$4wsiyx.3zqTJ3IxAZXL1zuRALy0m9EdB0miQfNLRq/OnbTE1byK3C");
        log.info("matches : {}",matches);

        NewMember newMember2 = securityService.createUser("aa", "aa123", passwordEncoder);
        log.info("newMember2 : {}",newMember2);

        NewMember newMember3 = securityService.createUser("bb", "bb123", passwordEncoder);
        log.info("newMember3 : {}",newMember3);

        NewMember newMember4 = securityService.createUser("cc", "cc123", passwordEncoder);
        log.info("newMember4 : {}",newMember4);



    }

}