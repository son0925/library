package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // H2 콘솔 경로는 모두 허용
                        .requestMatchers("/h2-console/**").permitAll()
                        // 그 외 요청도 모두 허용 (필요에 따라 변경)
                        .anyRequest().permitAll()
                )
                // H2 콘솔을 iframe으로 띄울 수 있도록 설정
                .headers(headers -> headers.frameOptions().sameOrigin())
                // CSRF 비활성화 (H2 콘솔은 POST 요청 시 CSRF 토큰 때문에 문제 발생할 수 있음)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
