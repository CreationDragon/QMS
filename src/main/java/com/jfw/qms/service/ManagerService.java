package com.jfw.qms.service;

import com.jfw.qms.entity.Question;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;

import java.util.List;

public interface ManagerService {
    List<com.jfw.qms.model.User> getUser();

    Integer deleteUser(String userID);

    com.jfw.qms.model.User getUserInfoById(String userId);

    ThreeArea getAreaById(String provinceID, String cityID, String districtID);

    String editUser(User user, String userID);

    List<com.jfw.qms.model.Question> getQuestionnaire();

    Integer deleteQues(String quesID);

    Question getQuestionInfoById(String quesID);

    String editQues(String title, String answerA, String answerB, String answerC, String answerD, String quesID);
}
