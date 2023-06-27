package com.example.blog.repository;

import com.example.blog.dto.NewMember;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class NewMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 테스트(){
        Optional<NewMember> admin = memberRepository.findByUserId("admin");
        log.info("id : {}",admin.get().getId());
        log.info("userid : {}",admin.get().getUserId());
        log.info("pw : {}",admin.get().getPw());
        log.info("role : {}",admin.get().getRoles());
    }

}