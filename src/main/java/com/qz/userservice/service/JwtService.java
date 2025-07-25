package com.qz.userservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtService {

    private final String ROLES = "ROLES";
    private final SecretKey secretKey = generateKey();

    public String generateToken(Authentication authentication) {

        List<String> roles = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> claims = new HashMap<>();
        claims.put(ROLES, roles);

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .setIssuer("Beast Boy")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication verifyToken(String token) {
        try {
            Claims claims = parseClaims(token);
            String subject = claims.getSubject();
            List<?> roleObjects = claims.get(ROLES, List.class);
            List<GrantedAuthority> roles = roleObjects.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return UsernamePasswordAuthenticationToken.authenticated(subject, null, roles);
        } catch (Exception e) {
            throw new JwtException("Invalid Token");
        }
    }

    public Claims parseClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public SecretKey generateKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

}
