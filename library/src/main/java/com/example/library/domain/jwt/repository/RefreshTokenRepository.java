package com.example.library.domain.jwt.repository;

import com.example.library.domain.jwt.model.RefreshTokenEntity;
import com.example.library.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByUser_username(String username);
    Optional<RefreshTokenEntity> findByUser(UserEntity user);

}
