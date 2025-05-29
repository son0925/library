package com.example.library.domain.auth.controller;

import com.example.library.domain.auth.model.AccessTokenRequest;
import com.example.library.domain.auth.service.TokenAuthenticationService;
import com.example.library.domain.jwt.model.RefreshAccessTokenRequest;
import com.example.library.domain.jwt.model.TokenResponse;
import com.example.library.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
            @RequestBody RefreshAccessTokenRequest request
    ) {
        log.info("requestToken : {}", request);
        return tokenAuthenticationService.getAccessTokenByRefreshToken(request.getRefreshToken());
    }

}
