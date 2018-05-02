package com.jfw.qms.service.impl;

import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.entity.UserQuestionnaire;
import com.jfw.qms.model.Area;
import com.jfw.qms.model.CustomQuestionnaire;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;
import com.jfw.qms.repository.IndexRepository;
import com.jfw.qms.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexRepository indexRepository;
    private String msg;
    private User user;
    private List<Question> questionList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<CustomQuestionnaire> CQList = new ArrayList<>();

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

    @Override
    public List<Question> quesSearch(String keyword) {
        questionList = indexRepository.quesSearch(keyword);
        return questionList;
    }

    @Override
    public List<User> getAdminInfo() {
        userList = indexRepository.getAdminInfo();
        return userList;
    }

    @Override
    public int putMessage(Message data, Integer userID) {
        int count = indexRepository.putMessage(data, userID);
        return count;
    }

    @Override
    public List<CustomQuestionnaire> getAllQues() {
        CQList = indexRepository.getAllQues();
        return CQList;
    }

    @Override
    public List<Question> getQuesById(Integer quesID) {
        questionList = indexRepository.getQuesById(quesID);
        return questionList;
    }

    @Override
    public List<CustomQuestionnaire> getQuesFromById(Integer quesId) {
        CQList = indexRepository.getQuesFromById(quesId);
        return CQList;
    }

    @Override
    public Integer setAnswer(Integer userID, Map<Integer, String> map, Integer questionnaireID) {
        Integer value = indexRepository.setAnswer(userID, map, questionnaireID);
        return value;
    }

    @Override
    public List<CustomQuestionnaire> geHotQues() {
        CQList = indexRepository.getHotQues();
        return CQList;
    }

    @Override
    public List<UserQuestionnaire> getChartInfo() {
        List<UserQuestionnaire> userQuestionnaires = indexRepository.getChartInfo();
        return userQuestionnaires;
    }

    @Override
    public void updateUserHead(String myFileName, Integer userId) {
        indexRepository.updateUserHead(myFileName, userId);
    }

    @Override
    public String getPassword(Integer questionnaireId) {
        String password = indexRepository.getPassword(questionnaireId);
        return password;
    }

    @Override
    public Integer getRegistersId() {
        Integer userId = indexRepository.getRegistersId();
        return userId;
    }
}
