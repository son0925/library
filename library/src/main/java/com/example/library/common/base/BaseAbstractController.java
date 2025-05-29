package com.example.library.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseAbstractController<
        ENTITY extends BaseEntity,
        REQ,
        RES,
        REPO extends JpaRepository<ENTITY, Integer>,
        SERV extends BaseAbstractService<ENTITY, REQ, RES, REPO, CONV>,
        CONV extends BaseAbstractConverter<ENTITY, REQ, RES>
        > implements BaseInterfaceController<REQ, RES> {

    protected final SERV service;

    public BaseAbstractController(
            SERV service
    ) {
        this.service = service;
    }

    @Override
    @PostMapping
    public RES create(REQ request) {
        return service.create(request);
    }

    @Override
    @PutMapping("/{id}")
    public RES update(
            @PathVariable("id") Integer id,
            @RequestBody REQ request
    ) {
        return service.update(id, request);
    }

    @Override
    @GetMapping("/{id}")
    public RES read(@PathVariable("id") Integer id) {
        return service.read(id);
    }

    @Override
    @GetMapping("/list")
    public List<RES> readAll() {
        return service.readAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        service.delete(id);
    }
}
