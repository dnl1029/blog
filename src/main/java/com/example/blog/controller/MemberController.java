package com.example.blog.controller;

import com.example.blog.dto.MemberDto;
import com.example.blog.service.JwtIssueService;
import com.example.blog.service.MemberService;
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

    // MemberController 내 범위에서 RuntimeException 발생하면, runtimeHandler가 처리한다.
    @ExceptionHandler(value = RuntimeException.class)
    public Object runtimeHandler(Exception e) {
        log.info(e.getMessage());
        return "id/password 인증에 실패했습니다.";
    }

}
