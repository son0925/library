package com.example.library.domain.auth.service;

import com.example.library.domain.auth.model.AccessTokenRequest;
import com.example.library.domain.jwt.model.TokenResponse;
import com.example.library.domain.jwt.service.JwtService;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.service.UserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse generateToken(AccessTokenRequest request) throws Exception {
        UserEntity user = userService.findByUsernameWithThrow(request.getUsername(), new JwtException(""));
        passwordMatches(request.getPassword(), user.getPassword());
        return jwtService.getTokenByUser(user);
    }

    public TokenResponse getAccessTokenByRefreshToken(String refreshToken) {
        return jwtService.getAccessTokenByRefreshToken(refreshToken);
    }

    private void passwordMatches(String comparePassword, String savedPassword) {
        if (passwordEncoder.matches(comparePassword, savedPassword)) {
            throw new JwtException("");
        }
    }

}
