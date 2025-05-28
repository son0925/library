package com.example.library.domain.auth.model;

import lombok.Data;

@Data
public class AccessTokenRequest {

    private String username;

    private String password;

}
