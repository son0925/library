package com.example.library.domain.jwt.model;

import com.example.library.common.base.BaseEntity;
import com.example.library.domain.user.model.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenEntity extends BaseEntity {

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne
    private UserEntity user;

}
