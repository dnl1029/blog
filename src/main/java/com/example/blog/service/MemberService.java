package com.example.blog.service;

import com.example.blog.dto.MemberDto;
import com.example.blog.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberDto getMemberByDto(MemberDto memberDto){
        LinkedHashMap<String,Object> paramMap = new LinkedHashMap<>();

        log.info("id : {}",memberDto.getUserId());
        log.info("password : {}",memberDto.getPassword());

        paramMap.put("USER_ID",memberDto.getUserId());
        paramMap.put("PASSWORD",memberDto.getPassword());

        MemberDto result = memberMapper.findMemberByMap(paramMap);
        if(result == null) {
            throw new RuntimeException();
        }
        return result;
    }

}
