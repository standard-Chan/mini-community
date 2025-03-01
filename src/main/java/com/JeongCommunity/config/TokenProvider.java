package com.JeongCommunity.config;

import com.JeongCommunity.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    // Token 반환 메서드
    //  expiredAt : 키 유효 시간
    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // token 생성 메서드
    private String makeToken(Date expiry, User user) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header type 설정 : JWT
                .setIssuer(jwtProperties.getIssuer()) // payload : issue 내용
                .setIssuedAt(now) // payload :  issue 시간
                .setExpiration(expiry) // payload : 만료시간
                .setSubject(user.getEmail()) // payload : subject 설정
                .claim("id", user.getId()) // claim ID : claim은 사용자 정보
                .signWith(SignatureAlgorithm.ES256, jwtProperties.getSecretKey()) // sign : 해시 알고리즘과 비밀키. sign은 헤더와 페이로드를 합쳐서 만든 값
                .compact();
    }

    // 토큰이 유효한지 확인
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰을 받고 인증 정보 반환
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework
                .security.core.userdetails.User(claims.getSubject()
                , "", authorities), token, authorities);
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
