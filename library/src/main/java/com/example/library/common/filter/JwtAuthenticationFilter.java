package com.example.library.common.filter;

import com.example.library.domain.jwt.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final static String HEADER_STRING = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/auth") || requestURI.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("여까지 왔음");

        String token = getTokenString(request);

        try {
            if (token != null) {
                log.info("일단 여기까지");
                Authentication authentication = jwtService.verifyToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            // TODO NULL 터짐요
        } catch(ExpiredJwtException | SignatureException | IllegalArgumentException | UsernameNotFoundException e){
            // TODO 예외 처리하기
            log.error("Token Error");
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenString(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
