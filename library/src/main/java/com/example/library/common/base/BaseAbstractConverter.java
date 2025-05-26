package com.example.library.common.base;

import org.springframework.data.domain.Page;

import java.util.List;

public abstract class BaseAbstractConverter<ENTITY, REQ, RES> implements BaseInterfaceConverter<ENTITY, REQ, RES> {

    @Override
    public List<RES> toResponseList(List<ENTITY> entityList) {
        return entityList.stream()
                .map(this::toResponse)
                .toList()
                ;
    }

    @Override
    public Page<RES> toResponsePage(Page<ENTITY> entityPage) {
        return entityPage.map(this::toResponse);
    }

}
