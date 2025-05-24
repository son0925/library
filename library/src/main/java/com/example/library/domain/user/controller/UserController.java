package com.example.library.domain.user.controller;

import com.example.library.common.base.BaseAbstractController;
import com.example.library.domain.user.converter.UserConverter;
import com.example.library.domain.user.model.UserEntity;
import com.example.library.domain.user.model.UserRequest;
import com.example.library.domain.user.model.UserResponse;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.domain.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseAbstractController<
        UserEntity,
        UserRequest,
        UserResponse,
        UserRepository,
        UserService,
        UserConverter> {

    private final UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/{id}")
    public UserResponse read(
            @PathVariable("id") Integer id
    ) {
        return super.read(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public List<UserResponse> readAll() {
        return super.readAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public UserResponse update(
            @PathVariable("id") Integer id,
            @RequestBody UserRequest request
    ) {
        return super.update(id, request);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Integer id
    ) {
        super.delete(id);
    }
}
