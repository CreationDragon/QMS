package com.jfw.qms.service;

import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.model.Area;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;

import java.util.List;

public interface IndexService {
    Area getArea(String id, String sign);

    String registerNumber(com.jfw.qms.model.User user);

    User getUserInfo();

    User login(String userName, String userPsw);

    ThreeArea getAreaById(String provinceID, String cityID, String districtID);

    List<Question> quesSearch(String keyword);

    List<User> getAdminInfo();

    int putMessage(Message data, Integer userID);

    List<Integer> getAllQues();

    List<Question> getQuesById(Integer quesID);
}
