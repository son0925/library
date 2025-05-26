package com.example.library.domain.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    private String username;

    private String password;

    private String confirmPassword;

    private String name;

    private String phone;

    private String memo;

}
