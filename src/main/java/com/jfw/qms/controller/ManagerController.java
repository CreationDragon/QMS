package com.jfw.qms.controller;

import com.alibaba.fastjson.JSON;
import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.model.JsonResult;
import com.jfw.qms.model.QuestionnaireInfo;
import com.jfw.qms.model.TableInfo;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerController {
    private JsonResult result;
    private com.jfw.qms.model.User user;
    private List<com.jfw.qms.model.User> users = new ArrayList<>();
    private List<com.jfw.qms.model.Question> questions = new ArrayList<>();
    private TableInfo tableInfo = new TableInfo();
    private QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
    private String msg;
    private List<com.jfw.qms.model.Message> messageList = new ArrayList<>();
    @Autowired
    private ManagerService managerService;

    @GetMapping(path = "/admin/getUser")
    public String getuser() {
        result = new JsonResult();
        users = managerService.getUser();
        tableInfo.setCode(0);
        tableInfo.setMsg("");
        tableInfo.setCount(users.size());
        tableInfo.setData(users);

        String userinfo = JSON.toJSONString(tableInfo);

        return userinfo;
    }

    @GetMapping(path = "/admin/getQuestionnaire")
    public String getQuestionnaire() {


        questions = managerService.getQuestionnaire();
        questionnaireInfo.setCode(0);
        questionnaireInfo.setMsg("");
        questionnaireInfo.setCount(questions.size());
        questionnaireInfo.setData(questions);

        String questinfo = JSON.toJSONString(questionnaireInfo);

        return questinfo;
    }

    @PostMapping(path = "/admin/deleteUser")
    public JsonResult deleteUser(@RequestParam String userID) {
        System.out.println("所要删除的用户的ID是:    " + userID);

        result = new JsonResult();
        Integer res = managerService.deleteUser(userID);
        if (null != res) {
            result.setResult("success");
        } else {
            result.setResult("faile");
        }
        return result;
    }

    @PostMapping(path = "/admin/deleteQues")
    public JsonResult deleteQues(@RequestParam String quesID) {
        result = new JsonResult();

        Integer res = managerService.deleteQues(quesID);
        if (null != res) {
            result.setResult("success");
        } else {
            result.setResult("faile");
        }
        return result;
    }

    @PostMapping(path = "/admin/getUserInfoById")
    public JsonResult getUserInfoById(@RequestParam String userId) {
        result = new JsonResult();
        user = new com.jfw.qms.model.User();
        user = managerService.getUserInfoById(userId);

        result.setResult("success");
        result.setData(user);
        return result;

    }


    @PostMapping(path = "/admin/getQuesInfoById")
    public JsonResult getQuesInfoById(@RequestParam String quesID) {
        result = new JsonResult();
        Question question = new Question();

        question = managerService.getQuestionInfoById(quesID);
        result.setResult("success");
        result.setData(question);
        return result;

    }

    @PostMapping(path = "/admin/getAreaById")
    public JsonResult getUserInfoById(@RequestParam String provinceID, @RequestParam String cityID, @RequestParam String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = managerService.getAreaById(provinceID, cityID, districtID);

        result.setResult("success");
        result.setData(threeArea);
        return result;

    }

    @PostMapping(path = "/admin/editUser")
    public JsonResult editUser(@RequestParam String register_data, @RequestParam String userID, HttpServletRequest request, HttpServletResponse response) {
        result = new JsonResult();

        com.jfw.qms.model.User user = JSON.parseObject(register_data, com.jfw.qms.model.User.class);
        System.out.println(user);

        msg = managerService.editUser(user, userID);

        result.setResult("success");
        result.setData(msg);
        return result;
    }

    @PostMapping(path = "/admin/editQues")
    public JsonResult editQues(@RequestParam String title, @RequestParam String answerA, @RequestParam String answerB,
                               @RequestParam String answerC, @RequestParam String answerD, @RequestParam String quesID) {
        result = new JsonResult();

        msg = managerService.editQues(title, answerA, answerB, answerC, answerD, quesID);

        result.setResult("success");
        result.setData(msg);
        return result;

    }

    @PostMapping(path = "/admin/getMessage")
    public JsonResult getMessage(@RequestParam String adminID) {
        result = new JsonResult();
        messageList = managerService.getMessage(adminID);

        result.setResult("success");
        result.setData(messageList);

        return result;

    }


    @PostMapping(path = "/admin/getMessageById")
    public JsonResult getMessageById(@RequestParam String adminID, @RequestParam String messageID) {
        result = new JsonResult();
        messageList = managerService.getMessageById(adminID, messageID);

        result.setResult("success");
        result.setData(messageList);

        return result;

    }

    @PostMapping(path = "/admin/replyUser")
    public JsonResult replyUser(@RequestParam Integer messageID, @RequestParam String userEmail, @RequestParam String replyContent, @RequestParam String adminEmail, @RequestParam String userName) {
        result = new JsonResult();
        boolean flag = managerService.replyUser(messageID, userEmail, replyContent, adminEmail, userName);
        if (flag) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }

        return result;

    }


}
