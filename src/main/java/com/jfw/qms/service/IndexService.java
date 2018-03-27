package com.jfw.qms.service;

import com.jfw.qms.model.Area;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;

public interface IndexService {
    Area getArea(String id, String sign);

    String registerNumber(com.jfw.qms.model.User user);

    User getUserInfo();

    User login(String userName, String userPsw);

    ThreeArea getAreaById(String provinceID, String cityID, String districtID);
}
