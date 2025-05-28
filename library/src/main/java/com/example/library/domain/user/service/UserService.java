package com.example.library.domain.user.service;

import com.example.library.common.base.BaseAbstractService;
import com.example.library.common.exception.SuspendedException;
import com.example.library.domain.user.converter.UserConverter;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.model.UserRequest;
import com.example.library.domain.user.model.UserResponse;
import com.example.library.domain.user.model.UserStatus;
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

    public void throwIfSuspended(UserEntity user) {
        if (user.getStatus().equals(UserStatus.SUSPENDED)) {
            throw new SuspendedException();
        }
    }

    public UserEntity findByPhoneWithThrow(String phone) {
        return repository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    public UserEntity findByUsernameWithThrow(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public UserEntity findByUsernameWithThrow(String username, Exception exception) throws Exception {
        return repository.findByUsername(username)
                .orElseThrow(() -> exception);
    }

    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username)
                .orElse(null)
                ;
    }
}
