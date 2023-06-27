package com.example.blog.service;

import com.example.blog.dto.NewMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityService implements UserDetailsService {

    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
        Optional<NewMember> findOne = loginService.findOne(insertedUserId);
        log.info("findOne : {}",findOne);
        NewMember newMember = findOne.orElseThrow(() -> new UsernameNotFoundException("등록되지 않은 ID입니다."));

        return User.builder()
                .username(newMember.getUserId())
                .password(newMember.getPw())
                .roles(newMember.getRoles())
                .build();
    }

    public NewMember createUser(String userId, String pw, PasswordEncoder passwordEncoder) {
        NewMember newMember = new NewMember();
        newMember.setId(null);
        newMember.setUserId(userId);
        newMember.setPw(passwordEncoder.encode(pw));
        newMember.setRoles("USER");
        return newMember;
    }

}
