package com.example.library.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "loans")
public class BookLoansConfig {

    // 대출 기한 (일 단위)
    private int loanPeriodDays;

    // 1인당 최대 대출 가능 권수
    private int maxBooksPerUser;

}
