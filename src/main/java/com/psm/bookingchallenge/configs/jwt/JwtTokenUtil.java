package com.psm.bookingchallenge.configs.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.expiration}")
    private Integer expiration;

    @Value("${jwt.refresh.token.expiration}")
    private Integer refreshExpiration;


    public String generateToken(String username, Boolean isRefresh) {

        var finalExpiration = expiration;
        if (isRefresh) {
            finalExpiration = refreshExpiration;
        }
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date(System.currentTimeMillis() + finalExpiration.longValue()))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }


    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
    }


    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }


    public Boolean isTokenValid(String token)  {
        Claims claims = getClaims(token);
        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());
        return now.before(expirationDate);
    }

    public String generateTokenForHeader(String username) {
        String token = generateToken(username, false);
        String refreshToken = generateToken(username, true);

        return token+"/"+refreshToken;
    }


}
