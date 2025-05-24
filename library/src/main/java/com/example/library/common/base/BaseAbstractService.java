package com.example.library.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseAbstractService<ENTITY extends BaseEntity<REQ>, REQ, RES, REPO extends JpaRepository<ENTITY, Integer>, CONV extends BaseAbstractConverter<ENTITY, REQ, RES>> implements BaseInterfaceService<REQ, RES> {

    protected final REPO repository;
    protected final CONV converter;

    protected BaseAbstractService(REPO repository,
                                  CONV converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RES create(REQ request) {
        if (request == null) {
            throw new IllegalArgumentException();
        }

        ENTITY entity = converter.toEntity(request);

        repository.save(entity);

        return converter.toResponse(entity);
    }

    @Override
    public RES read(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException();
        }

        ENTITY entity = findByIdWithThrow(id);

        return converter.toResponse(entity);
    }

    @Override
    public List<RES> readAll() {
        List<ENTITY> entityList = repository.findAll();

        return converter.toResponseList(entityList);
    }

    @Override
    @Transactional
    public RES update(Integer id, REQ request) {
        ENTITY entity = findByIdWithThrow(id);

        entity.updateFromRequest(request);

        return converter.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public ENTITY findByIdWithThrow(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
