package com.example.library.domain.jwt.service;

import com.example.library.common.enums.TokenType;
import com.example.library.common.exception.BadRequestException;
import com.example.library.config.JwtConfig;
import com.example.library.domain.jwt.model.RefreshTokenEntity;
import com.example.library.domain.jwt.model.TokenResponse;
import com.example.library.domain.jwt.repository.RefreshTokenRepository;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.service.UserService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {

    private final JwtConfig jwtConfig;
    private final JwtParser accessParser;
    private final JwtParser refreshParser;
    private final SecretKey accessKey;
    private final SecretKey refreshKey;

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    public JwtService(JwtConfig jwtConfig,
                      UserDetailsService userDetailsService,
                      UserService userService,
                      RefreshTokenRepository refreshTokenRepository) {
        this.jwtConfig = jwtConfig;
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtConfig.getAccessSecretKey()));
        this.accessParser = Jwts.parser().verifyWith(accessKey).build();
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtConfig.getRefreshSecretKey()));
        this.refreshParser = Jwts.parser().verifyWith(refreshKey).build();
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // 토큰 발급
    public String generateToken(String username, TokenType type) {
        if (type == null) {
            type = TokenType.ACCESS_TOKEN;
        }

        // 유효기간
        Date expiration = getExpiration(type);
        // 비밀키
        SecretKey secretKey = getSecretKey(type);

        return Jwts.builder()
                .issuer(jwtConfig.getIssuer())
                .subject(username)
                .expiration(expiration)
                .signWith(secretKey)
                .compact()
                ;
    }


    // 적합한 사용자가 아닐 때 Exception
    // TODO ExceptionHandler 만들기
    public Authentication verifyToken(String token) throws JwtException, UsernameNotFoundException {
        String username = accessParser.parseSignedClaims(token).getPayload().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

    @Transactional
    public TokenResponse getTokenByUser(UserEntity user) {
        String accessToken = generateToken(user.getUsername(), TokenType.ACCESS_TOKEN);
        String refreshToken = generateToken(user.getUsername(), TokenType.REFRESH_TOKEN);

        // RefreshToken 저장
        RefreshTokenEntity rfEntity = refreshTokenRepository.findByUser(user)
                .orElse(null);

        if (rfEntity == null) {
            rfEntity = new RefreshTokenEntity();
            rfEntity.setUser(user);
        }

        rfEntity.setRefreshToken(refreshToken);

        refreshTokenRepository.save(rfEntity);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build()
                ;
    }


    // 토큰 재발급하기
    @Transactional
    public TokenResponse getAccessTokenByRefreshToken(String token) {
        String username = refreshParser.parseSignedClaims(token).getPayload().getSubject();
        UserEntity user = userService.findByUsernameWithThrow(username); // 유저가 없을 때 Exception
        RefreshTokenEntity refreshToken = findByUsernameWithThrow(username);

        if (!refreshToken.getRefreshToken().equals(token)) {
            refreshTokenRepository.delete(refreshToken);
            throw new JwtException("비정상적인 회원");
        }

        return getTokenByUser(user);
    }


    private Date getExpiration(TokenType type) {
        Duration duration = getDuration(type);
        Date now = new Date();
        return new Date(now.getTime() + duration.toMillis());
    }

    private Duration getDuration(TokenType type) {
        if (type.equals(TokenType.ACCESS_TOKEN)) {
            return Duration.ofMinutes(jwtConfig.getAccessDuration());
        }
        return Duration.ofMinutes(jwtConfig.getRefreshDuration());
    }

    private SecretKey getSecretKey(TokenType type) {
        if (type.equals(TokenType.ACCESS_TOKEN)) {
            return accessKey;
        }
        return refreshKey;
    }

    private RefreshTokenEntity findByUsernameWithThrow(String username) {
        return refreshTokenRepository.findByUser_username(username)
                .orElseThrow(BadRequestException::new);
    }

}
