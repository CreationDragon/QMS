package com.jfw.qms.service.impl;

import com.jfw.qms.model.Area;
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
    public String registerNumber(User user) {
        msg = indexRepository.registerNumber(user);
        return msg;
    }
}
