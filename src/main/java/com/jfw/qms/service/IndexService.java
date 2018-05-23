package com.jfw.qms.service;

import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.entity.UserQuestionnaire;
import com.jfw.qms.model.*;

import java.util.List;
import java.util.Map;

public interface IndexService {
    Area getArea(String id, String sign);

    String registerNumber(com.jfw.qms.model.User user);

    User getUserInfo(Integer userID);

    User login(String userName, String userPsw);

    ThreeArea getAreaById(String provinceID, String cityID, String districtID);

    List<Question> quesSearch(String keyword);

    List<User> getAdminInfo();

    int putMessage(Message data, Integer userID);

    List<CustomQuestionnaire> getAllQues();

    List<Question> getQuesById(Integer quesID);

    List<CustomQuestionnaire> getQuesFromById(Integer quesId);

    Integer setAnswer(Integer userID, Map<Integer, String> answer, Integer questionnaireID);

    List<CustomQuestionnaire> geHotQues();

    List<UserQuestionnaire> getChartInfo();

    void updateUserHead(String myFileName, Integer integer);

    String getPassword(Integer questionnaireId);

    Integer getRegistersId();

    List<Question> getQuests(Integer surveyID);

    Ensemble getQuesAnswerById(Integer quesId);
}
