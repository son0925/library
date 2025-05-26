package com.example.library.domain.user.controller;

import com.example.library.common.base.BaseAbstractController;
import com.example.library.domain.user.converter.UserConverter;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.model.UserRequest;
import com.example.library.domain.user.model.UserResponse;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    public UserResponse read(
            @PathVariable("id") Integer id
    ) {
        return userService.read(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<UserResponse> readAll() {
        return userService.readAll();
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable("id") Integer id,
            @RequestBody UserRequest request
    ) {
        return userService.update(id, request);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Integer id
    ) {
        userService.delete(id);
    }
}
