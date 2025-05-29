package com.example.library.domain.user.model;

import com.example.library.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = false)
    private String phone;

    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false)
    private LocalDateTime createAt;

    private LocalDateTime suspendedAt;

    public void updateFromRequest(UserRequest request) {
        this.memo = request.getMemo();
        this.name = request.getName();
        this.phone = request.getPhone();
    }

    public void setSuspended() {
        this.suspendedAt = LocalDateTime.now();
        this.status = UserStatus.SUSPENDED;
    }
}
