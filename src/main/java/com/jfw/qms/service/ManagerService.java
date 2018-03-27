package com.jfw.qms.service;

import com.jfw.qms.model.ThreeArea;

import java.util.List;

public interface ManagerService {
    List<com.jfw.qms.model.User> getUser();

    Integer deleteUser(String userID);

    com.jfw.qms.model.User getUserInfoById(String userId);

    ThreeArea getAreaById(String provinceID, String cityID, String districtID);
}
