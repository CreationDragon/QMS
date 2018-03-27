package com.jfw.qms.service.impl;

import com.jfw.qms.model.Area;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;
import com.jfw.qms.repository.IndexRepository;
import com.jfw.qms.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexRepository indexRepository;
    private String msg;
    private User user;

    @Override
    public Area getArea(String id, String sign) {
        Integer count = null;
        if ("" == id) {
            count = null;
        } else {
//            count = Integer.parseInt(id) + 1;
            count = Integer.parseInt(id);
        }
        Area area = indexRepository.getArea(count, sign);
        return area;
    }

    @Override
    public String registerNumber(com.jfw.qms.model.User user) {
        msg = indexRepository.registerNumber(user);
        return msg;
    }

    @Override
    public User getUserInfo() {

        user = indexRepository.getUserInfo();
        return user;
    }

    @Override
    public User login(String userName, String userPsw) {
        user = indexRepository.login(userName, userPsw);
        return user;
    }

    @Override
    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = indexRepository.getAreaById(provinceID, cityID, districtID);
        return threeArea;
    }
}
