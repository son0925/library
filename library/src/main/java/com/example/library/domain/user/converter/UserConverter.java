package com.example.library.domain.user.converter;

import com.example.library.common.base.BaseAbstractConverter;
import com.example.library.domain.user.model.Role;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.model.UserRequest;
import com.example.library.domain.user.model.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter extends BaseAbstractConverter<UserEntity, UserRequest, UserResponse> {

    @Override
    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .status(userEntity.getStatus())
                .memo(userEntity.getMemo())
                .build()
                ;
    }

    @Override
    public UserEntity toEntity(UserRequest request) {
        return UserEntity.builder()
                .name(request.getName())
                .password(request.getPassword())
                .role(Role.USER)
                .phone(request.getPhone())
                .memo(request.getMemo())
                .build()
                ;
    }

}
