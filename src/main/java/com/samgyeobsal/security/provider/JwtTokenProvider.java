package com.samgyeobsal.security.provider;

import com.samgyeobsal.security.service.FormUserDetailService;
import com.samgyeobsal.type.JwtStatus;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {

    private final FormUserDetailService userDetailService;

    private final String secretKey;
    private final long tokenValidityInMs;
    private final long refreshTokenValidityInMs;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.token-validity-in-sec}") long tokenValidity,
                            @Value("${jwt.refresh-token-validity-in-sec}") long refreshTokenValidity,
                            FormUserDetailService userDetailService){
        this.secretKey = secretKey;
        this.tokenValidityInMs = tokenValidity;
        this.refreshTokenValidityInMs = refreshTokenValidity;
        this.userDetailService = userDetailService;
    }

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }

    public String createAccessToken(String userEmail){
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMs);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken(String userEmail){
        Claims claims = Jwts.claims().setSubject(userEmail);

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidityInMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    public Authentication getAuthentication(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        UserDetails userDetails = userDetailService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, jwt, userDetails.getAuthorities());
    }
    public String getUserEmail(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }

    public JwtStatus validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            log.info("token access = {}",token);
            return JwtStatus.ACCESS;
        }catch (ExpiredJwtException e){
            log.info("token expired = {}", token);
            return JwtStatus.EXPIRED;
        }catch (JwtException | IllegalArgumentException e){
//            log.info("jwtException", e);
        }
        return JwtStatus.DENIED;
    }
}
