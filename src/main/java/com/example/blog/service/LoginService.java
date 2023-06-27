package com.example.blog.service;

import com.example.blog.dto.NewMember;
import com.example.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Optional<NewMember> findOne(String userId) {
        return memberRepository.findByUserId(userId);
    }

}

