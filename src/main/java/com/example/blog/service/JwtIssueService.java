package com.example.blog.service;

import com.example.blog.dto.MemberDto;
import io.jsonwebtoken.*;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:key.properties") //src/main/resources/key.properties 참조
public class JwtIssueService {

    @Value("${jasypt.key}")
    private String jwtSecretKey;

    private final MemberService memberService;

    //jwt 토큰 생성 메서드
    public String createJwt(MemberDto memberDto) {
        Date now = new Date();
        MemberDto result = memberService.getMemberByDto(memberDto);

        if(result == null) {
            log.info("login failed. id:{},password:{}",memberDto.getUserId(),memberDto.getPassword());
            throw new RuntimeException();
        }
        else {
            return Jwts.builder()
                    .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                    .setClaims(createClaims(result))
                    .setIssuedAt(now)
                    .setExpiration(createExpiredDate()) //발급날짜계산
                    .signWith(SignatureAlgorithm.HS256, createSignature())
                    .compact();
        }
    }

    //Claims 생성 메서드. payload 정보 세팅
    private Map<String, Object> createClaims(MemberDto memberDto) {
        //사용자의 id, password, role을 설정하여 payload에서 정보 조회
        Map<String, Object> claims = new HashMap<>();
        String role = "";
        if("admin".equals(memberDto.getUserId())){
            role = "ADMIN";
        }
        else{
            role = "USER";
        }
        log.info("userId : {}",memberDto.getUserId());
        //log.info("password : {}",memberDto.getPassword());
        log.info("role : {}",role);

        claims.put("userId", memberDto.getUserId());
        //claims.put("password", memberDto.getPassword());
        claims.put("name", memberDto.getName());
        claims.put("role", role);
        return claims;
    }

    // jwt 서명(Signature) 발급해주는 메서드
    private Key createSignature() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // jwt 복호화 메서드
    public Jws<Claims> getClaims(String jwt) {
        try{
            return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwt);
        }
        catch (SignatureException e){
            log.info(e.getMessage());
            return null;
        }
    }

    // jwt 만료날짜 메서드
    private Date createExpiredDate() {
        Calendar now = Calendar.getInstance();
        //만료날짜 3년
        now.add(Calendar.YEAR,3);
        return now.getTime();
    }

    // jwt 유효성 체크 메서드
    public boolean tokenValidCheck(String jwt) {
        try{
            Jws<Claims> claims = getClaims(jwt);
            log.info("expireTime : {}",claims.getBody().getExpiration());
            log.info("userID : {}",claims.getBody().get("userId"));
            log.info("role : {}",claims.getBody().get("role"));
            return true;
        }
        catch (ExpiredJwtException e){
            log.error("Token has expired",e.getMessage(),e);
            return false;
        }
        catch (JwtException e){
            log.error("JwtException error",e.getMessage(),e);
            return false;
        }
        catch (NullPointerException e){
            log.error("Token is null",e.getMessage(),e);
            return false;
        }
    }

}
