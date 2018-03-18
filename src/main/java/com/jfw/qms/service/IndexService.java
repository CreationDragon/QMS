package com.jfw.qms.service;

import com.jfw.qms.model.Area;
import com.jfw.qms.model.User;

public interface IndexService {
    Area getArea(String id, String sign);

    String registerNumber(User user);
}
