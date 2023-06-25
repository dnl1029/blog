package com.example.blog.mapper;

import com.example.blog.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;

@Mapper
public interface MemberMapper {

    MemberDto findMemberByMap(LinkedHashMap<String,Object> paramMap);

}
