package com.example.library.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(value = "jwt")
public class JwtConfig {

    private String issuer;

    private String accessSecretKey;

    private String refreshSecretKey;

    private int accessDuration;

    private int refreshDuration;

}
