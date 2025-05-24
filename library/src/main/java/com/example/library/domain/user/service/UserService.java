package com.example.library.domain.user.service;

import com.example.library.common.base.BaseAbstractService;
import com.example.library.domain.user.converter.UserConverter;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.model.UserRequest;
import com.example.library.domain.user.model.UserResponse;
import com.example.library.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseAbstractService<
        UserEntity,
        UserRequest,
        UserResponse,
        UserRepository,
        UserConverter> {

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        super(userRepository, userConverter);
    }

}
