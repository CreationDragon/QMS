package com.jfw.qms.service.impl;

import com.jfw.qms.entity.Question;
import com.jfw.qms.entity.Questionnaire;
import com.jfw.qms.entity.User;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.repository.ManagerRepository;
import com.jfw.qms.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    private List<User> users = new ArrayList<>();
    private List<com.jfw.qms.model.User> userList;
    private com.jfw.qms.model.User user;
    private List<Question> questions = new ArrayList<>();
    private List<com.jfw.qms.model.Question> questionList = new ArrayList<>();
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<com.jfw.qms.model.User> getUser() {
        users = managerRepository.getUser();
        userList = new ArrayList<>();

        for (User u : users
                ) {
            String city = managerRepository.getCityById(u.getUserCity());
            String province = managerRepository.getProvinceById(u.getUserProvince());
            String district = managerRepository.getDistrictById(u.getUserDistrict());
            user = new com.jfw.qms.model.User();
            user.setUserId(u.getUserId());
            user.setUserName(u.getUserName());
            user.setUserAddressDetail(u.getUserAddressDetail());
            user.setUserAuthority(u.getUserAuthority());
            user.setCity(city);
            user.setProvince(province);
            user.setDistrict(district);
            user.setUserEmail(u.getUserEmail());
            user.setUserPhone(u.getUserPhone());
            user.setUserGener(u.getUserGener());
            user.setOption("<div><i class=\"layui-icon\" style=\"font-size: 30px;color: #009688\" id=\"edit\">&#xe642;</i></i><i class=\"layui-icon\"style=\"font-size: 30px;color: #009688\" id=\"del\">&#xe640;</i></div>");
            userList.add(user);
        }

        return userList;
    }

    @Override
    public Integer deleteUser(String userID) {
        Integer userid = Integer.parseInt(userID);
        Integer result = managerRepository.deleteUser(userid);
        return result;
    }

    @Override
    public com.jfw.qms.model.User getUserInfoById(String userId) {
        Integer userid = Integer.parseInt(userId);
        com.jfw.qms.model.User user = new com.jfw.qms.model.User();
        user = managerRepository.getUserInfoById(userid);
        return user;
    }

    @Override
    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = managerRepository.getAreaById(provinceID, cityID, districtID);
        return threeArea;
    }

    @Override
    public String editUser(com.jfw.qms.model.User user, String userID) {
        Integer userid = Integer.parseInt(userID);
        String msg = managerRepository.editUser(user, userid);
        return msg;
    }

    @Override
    public List<com.jfw.qms.model.Question> getQuestionnaire() {
        questionList = new ArrayList<>();
        questions = managerRepository.getQuestionnaire();
        com.jfw.qms.model.Question question = new com.jfw.qms.model.Question();

        for (Question q : questions
                ) {
            question = new com.jfw.qms.model.Question();
            question.setQuestionId(q.getQuestionId());
            question.setTitle(q.getTitle());
            question.setAnswerA(q.getAnswerA());
            question.setAnswerB(q.getAnswerB());
            question.setAnswerC(q.getAnswerC());
            question.setAnswerD(q.getAnswerD());
            question.setOption("<div><i class=\"layui-icon\" style=\"font-size: 30px;color: #009688\" id=\"ques_edit\">&#xe642;</i></i><i class=\"layui-icon\"style=\"font-size: 30px;color: #009688\" id=\"ques_del\">&#xe640;</i></div>");
            questionList.add(question);
        }


        return questionList;
    }

    @Override
    public Integer deleteQues(String quesID) {

        Integer quesid = Integer.parseInt(quesID);
        Integer result = managerRepository.deleteQues(quesid);
        return result;
    }

    @Override
    public Question getQuestionInfoById(String quesID) {
        Integer quesid = Integer.parseInt(quesID);
        Question question = new Question();
        question = managerRepository.getQuestionInfoById(quesid);
        return question;
    }

    @Override
    public String editQues(String title, String answerA, String answerB, String answerC, String answerD, String quesID) {
        Integer quesid = Integer.parseInt(quesID);
        String msg = managerRepository.editQues(title, answerA, answerB, answerC, answerD, quesid);
        return msg;
    }
}
