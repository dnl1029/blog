package com.example.blog.controller;

import com.example.blog.dto.MemberDto;
import com.example.blog.service.JwtIssueService;
import com.example.blog.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final JwtIssueService jwtIssueService;

    @GetMapping("mybatis/test")
    public MemberDto getMemberByIdPassword(@ModelAttribute MemberDto memberDto) {
        MemberDto result = memberService.getMemberByDto(memberDto);
        log.info("member : {}",result);
        return result;
    }

    @GetMapping("jwt/issue")
    public String getJwtToken(@ModelAttribute MemberDto memberDto) {
        String jwt = jwtIssueService.createJwt(memberDto);
        log.info("jwt token : {}",jwt);
        return jwt;
    }

    @GetMapping("jwt/claims")
    public String getClaims(String jwt) {
        Jws<Claims> claims = jwtIssueService.getClaims(jwt);
        log.info("userId : {},",claims.getBody().get("userId"));
        log.info("name : {}",claims.getBody().get("name"));
        //log.info("password = {},",claims.getBody().get("password"));
        log.info("Expired Date : {}",claims.getBody().getExpiration());
        return "ok";
    }

    @GetMapping("jwt/valid/check")
    public boolean checkValid(String jwt) {
        boolean b = jwtIssueService.tokenValidCheck(jwt);
        log.info("validation check : {}",b);
        return  b;
    }


    // MemberController 내 범위에서 RuntimeException 발생하면, runtimeHandler가 처리한다.
    @ExceptionHandler(value = RuntimeException.class)
    public Object runtimeHandler(Exception e) {
        log.info(e.getMessage());
        return "id/password 인증에 실패했습니다.";
    }

}
