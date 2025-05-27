package com.example.library.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {

    ACCESS_TOKEN(1),
    REFRESH_TOKEN(24)

    ;

    private final int duration;

}
