package com.example.library.common.base;

import java.util.List;

public interface BaseInterfaceService<REQ, RES> {

    // Create
    public RES create(REQ request);

    // Read (단일 객체)
    public RES read(Integer id);

    // Read (리스트)
    public List<RES> readAll();

    // Update
    public RES update(Integer id, REQ request);

    // Delete
    public void delete(Integer id);

}
