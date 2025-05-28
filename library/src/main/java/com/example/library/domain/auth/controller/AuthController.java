package com.example.library.domain.auth.controller;

import com.example.library.domain.auth.model.AccessTokenRequest;
import com.example.library.domain.auth.service.TokenAuthenticationService;
import com.example.library.domain.jwt.model.TokenResponse;
import com.example.library.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public TokenResponse login(
            @RequestBody AccessTokenRequest request
    ) throws Exception {
        return tokenAuthenticationService.generateToken(request);
    }

    @PostMapping("/login/token")
    public TokenResponse signup(
            @RequestBody String requestToken
    ) {
        return tokenAuthenticationService.getAccessTokenByRefreshToken(requestToken);
    }

}
