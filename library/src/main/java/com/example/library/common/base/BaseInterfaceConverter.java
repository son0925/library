package com.example.library.common.base;

import java.util.List;

public interface BaseInterfaceConverter<ENTITY, REQ, RES> {

    public RES toResponse(ENTITY entity);

    public ENTITY toEntity(REQ request);

    public List<RES> toResponseList(List<ENTITY> entityList);

}
