package com.example.library.domain.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Integer id;

    private String name;

    private String phone;

    private String memo;

    private UserStatus status;

}
