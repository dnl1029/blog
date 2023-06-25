package com.example.blog.controller;

import com.example.blog.dto.MemberDto;
import com.example.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("mybatis/test")
    public MemberDto getMemberByIdPassword(@ModelAttribute MemberDto memberDto) {
        MemberDto result = memberService.getMemberByDto(memberDto);
        log.info("member : {}",result);
        return result;
    }
}
