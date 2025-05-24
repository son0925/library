package com.example.library.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    ACTIVE("대출 가능"),
    SUSPENDED("대출 정지")

    ;

    private final String description;
}
