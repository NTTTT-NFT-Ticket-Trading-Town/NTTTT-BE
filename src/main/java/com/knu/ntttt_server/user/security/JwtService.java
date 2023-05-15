package com.knu.ntttt_server.user.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${value.jwt.secret-key}")
    private final String secretKey;
    private static final int EXPIRED_TIME = 1000*60*60*24*3; // 3 days

    /**
     * JWT 토큰 발행
     *
     * @param subject 유저를 구분하는 구분자 (현재는 nickname)
     * @return JWT token
     */
    public String createToken(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(Base64.getEncoder().encode(secretKey.getBytes()), signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .compact();
    }

    /**
     * JWT 토큰에서 구분자 추출
     *
     * @param token JWT 토큰
     * @return subject 유저를 구분하는 구분자 (현재는 nickname)
     */
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * Authorization: Bearer {Token} 부분의 Token을 parsing한다
     * @param request
     * @return
     */
    public String parseTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    // token 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.info("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.info("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims string is empty.");
        } catch (SignatureException ex) {
            log.info("the claimsJws JWS signature validation fails");
        }
        return false;
    }
}
