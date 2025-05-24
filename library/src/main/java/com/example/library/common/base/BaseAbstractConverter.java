package com.example.library.common.base;

import java.util.List;

public abstract class BaseAbstractConverter<ENTITY, REQ, RES> implements BaseInterfaceConverter<ENTITY, REQ, RES> {

    @Override
    public List<RES> toResponseList(List<ENTITY> entityList) {
        return entityList.stream()
                .map(this::toResponse)
                .toList()
                ;
    }
}
