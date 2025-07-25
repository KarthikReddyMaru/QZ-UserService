package com.qz.userservice.filter;

import com.qz.userservice.consts.Token;
import com.qz.userservice.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final String PREFIX = Token.PREFIX;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(Token.AUTHORIZATION);
        SecurityContext context = SecurityContextHolder.getContext();
        if(authorizationHeader != null && authorizationHeader.startsWith(PREFIX) && context.getAuthentication() == null) {
            String token = authorizationHeader.substring(7);
            Authentication authentication = jwtService.verifyToken(token);
            context.setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
