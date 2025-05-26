package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // H2 콘솔 경로, API 경로는 모두 허용
                        .requestMatchers("/h2-console/**", "/api/**", "/auth/**", "/**").permitAll()
                        // 책 대출, 반납 관련은 ADMIN 권한이여야 한다
                        .requestMatchers("/book-loans/**").hasRole("ADMIN")
                        // 그 외 요청은 모두 검사
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                        )))
                // CSRF 비활성화 (H2 콘솔은 POST 요청 시 CSRF 토큰 때문에 문제 발생할 수 있음)
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/h2-console/**")
//                );
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
