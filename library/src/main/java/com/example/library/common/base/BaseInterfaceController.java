package com.example.library.common.base;

import java.util.List;

public interface BaseInterfaceController<REQ, RES> {

    RES create(REQ request);

    RES update(Integer id, REQ request);

    RES read(Integer id);

    List<RES> readAll();

    void delete(Integer id);

}
