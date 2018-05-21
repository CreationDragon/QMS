package com.jfw.qms.controller;

import com.alibaba.fastjson.JSON;
import com.jfw.qms.common.TreeNode;
import com.jfw.qms.entity.AnswerCount;
import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.entity.UserQuestionnaire;
import com.jfw.qms.model.*;
import com.jfw.qms.service.IndexService;
import com.jfw.qms.utils.Logarithm;
import com.jfw.qms.test.Test;
import com.jfw.qms.utils.produceWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    private String msg;
    private HttpSession session;
    private JsonResult<Object> result = new JsonResult<>();
    private User user = new User();
    private String root = null;
    private AnswerCount answerCount = new AnswerCount();
    private DownloadRepsoe dp = new DownloadRepsoe();
    private List<Question> questionList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<CustomQuestionnaire> CQList = new ArrayList<>();
    private Map<String, Double> entropyMap = new HashMap<>();


    @RequestMapping(path = "/index")
    public JsonResult<Object> getIndex(HttpServletRequest request, Model model) {

        String userName = (String) request.getSession().getAttribute("userName");
        String userPsw = (String) request.getSession().getAttribute("userPsw");
        Integer userId = (Integer) request.getSession().getAttribute("user_id");

        user.setUserName(userName);
        user.setUserPsw(userPsw);
        user.setUserId(userId);


        result.setResult("success");
        result.setData(user);

        model.addAttribute("data", user);

        return result;
    }

    @RequestMapping(path = "/admin/index")
    public JsonResult<Object> getAdminIndex(HttpServletRequest request, Model model) {

        String userName = (String) request.getSession().getAttribute("user_name");
        String userPsw = (String) request.getSession().getAttribute("user_psw");

        user.setUserName(userName);
        user.setUserPsw(userPsw);


        result.setResult("success");
        result.setData(user);

        model.addAttribute("data", user);

        return result;
    }

    @PostMapping(path = "/getAreaById")
    public JsonResult<Object> getUserInfoById(@RequestParam String provinceID, @RequestParam String cityID, @RequestParam String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = indexService.getAreaById(provinceID, cityID, districtID);

        result.setResult("success");
        result.setData(threeArea);
        return result;

    }

    @GetMapping(path = "/area")
    public JsonResult<Area> getArea(@RequestParam String id, @RequestParam String sign) {
        Area area = indexService.getArea(id, sign);
        JsonResult<Area> result = new JsonResult<Area>();
        result.setResult("success");
        result.setData(area);
        return result;
    }

    @PostMapping(path = "/register")
    public JsonResult<String> registerNumber(@RequestParam String register_data, HttpServletRequest request, HttpServletResponse response) {
        com.jfw.qms.model.User user = JSON.parseObject(register_data, com.jfw.qms.model.User.class);
        System.out.println(user);


        msg = indexService.registerNumber(user);
        Integer userId = indexService.getRegistersId();

        JsonResult<String> result = new JsonResult<>();
        if (msg.equals("注册成功")) {
            result.setResult("success");
            //获取Session
            session = request.getSession();
            //将数据存储在Session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userPsw", user.getUserPsw());
            session.setAttribute("user_id", userId);
        } else {
            result.setResult("fail");
        }
        result.setData(msg);
        return result;
    }

    @PostMapping(path = "/getInfo")
    public JsonResult<Object> getUserInfo(@RequestParam Integer userID) {
        user = indexService.getUserInfo(userID);

        result.setResult("success");
        result.setData(user);
        return result;
    }

    @PostMapping(path = "/login")
    public JsonResult<Object> Login(HttpServletRequest request, @RequestParam String userName, @RequestParam String userPsw) {

        session = request.getSession();
        user = new User();
        user = indexService.login(userName, userPsw);

        if (1 == user.getUserAuthority()) {
            //将数据存储在Session中
            session.setAttribute("user_name", user.getUserName());
            session.setAttribute("user_psw", user.getUserPsw());
            session.setAttribute("user_authority", user.getUserAuthority());
            session.setAttribute("user_id", user.getUserId());

        } else {
            //将数据存储在Session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userPsw", user.getUserPsw());
            session.setAttribute("user_authority", user.getUserAuthority());
            session.setAttribute("user_id", user.getUserId());
        }

        if (user.getUserName() != null) {
            result.setResult("success");
            result.setData(session.getAttribute("user_authority"));
        } else {
            result.setResult("fail");
            result.setData("您还未主册");
        }
        return result;
    }

    @PostMapping(path = "/modifyUserInfo")
    public JsonResult<Object> modifyUserInfo(@RequestParam String userID) {
        result = new JsonResult<>();

        return result;
    }


    @PostMapping(path = "/loginout")
    public JsonResult<Object> loginout(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        if (session == null) {
            result.setResult("faile");
            result.setData("faile");
        } else {
            session.removeAttribute("userName");
            session.removeAttribute("userPsw");
            session.removeAttribute("user_id");

            result.setResult("success");
            result.setData("success");
        }

        return result;
    }


    @PostMapping(path = "/admin/loginout")
    public JsonResult<Object> adminLoginout(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        if (session == null) {
            result.setResult("faile");
            result.setData("faile");
        } else {
            session.removeAttribute("user_name");
            session.removeAttribute("user_psw");
            session.removeAttribute("user_id");

            result.setResult("success");
            result.setData("success");
        }

        return result;
    }


    @PostMapping(path = "/upload/head")
    public DownloadRepsoe upload(MultipartFile file, @RequestParam String userID) {
        String myFileName = null;
        dp = new DownloadRepsoe();

        if (null != file) {
            myFileName = file.getOriginalFilename();// 文件原名称
            try {
                root = String.valueOf(ResourceUtils.getURL("application.properties"));
                System.out.println(root);

            } catch (Exception e) {
                e.printStackTrace();
            }
            String pathname = root.split("file:/")[1].split("application.properties")[0] + "/static/headpic";
            File fileDir = new File(pathname);
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path = pathname + "/" + userID + ".jpg";
            File localFile = new File(path);
            try {
                file.transferTo(localFile);
                dp.setCode(0);
                dp.setMsg("");
                dp.setData(null);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件为空");
        }
        indexService.updateUserHead(myFileName, Integer.valueOf(userID));
        return dp;

    }

    @PostMapping(path = "/quesSearch")
    public JsonResult<Object> quesSearch(@RequestParam String keyword) {
        result = new JsonResult<>();
        questionList = indexService.quesSearch(keyword);
        if (questionList.size() != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(questionList);
        return result;

    }

    @PostMapping(path = "/getAdminInfo")
    public JsonResult<Object> getAdminInfo() {
        result = new JsonResult<>();
        userList = indexService.getAdminInfo();
        result.setResult("success");
        result.setData(userList);

        return result;
    }

    @PostMapping(path = "/putMessage")
    public JsonResult<Object> putMessage(@RequestParam String data, @RequestParam Integer userID) {
        Message message = JSON.parseObject(data, Message.class);
        result = new JsonResult<>();

        int count = indexService.putMessage(message, userID);

        result.setResult("success");
        if (count > 0) {
            result.setResult("success");
            result.setData("留言成功");
        } else if (count == 0) {
            result.setResult("faile");
            result.setData("留言失败");
        } else {
            result.setData("已经留言过相同的问题，无需重复留言");
        }
        return result;

    }

    //    单独的一个功能获取用户的ID
    @PostMapping("/getUserId")
    public Integer getUserId() {
        Integer id = null;
        try {
            Integer user_id = (Integer) session.getAttribute("user_id");
            id = user_id;
        } catch (Exception e) {
            id = null;
        }
        return id;
    }

    @PostMapping(path = "/getAllQues")
    public JsonResult<Object> getAllQues() {
        result = new JsonResult<>();
        List<Integer> quesIDs = new ArrayList<>();
        CQList = indexService.getAllQues();
        if (CQList.size() != 0) {
            result.setResult("success");
            result.setData(CQList);
        } else {
            result.setResult("fail");
            result.setData("");
        }
        return result;
    }

    @PostMapping(path = "/getQuesById")
    public JsonResult<Object> getQuesById(@RequestParam Integer quesID) {
        result = new JsonResult<>();
        questionList = indexService.getQuesById(quesID);
        if (questionList.size() != 0) {
            result.setResult("success");
            result.setData(questionList);
        } else {
            result.setResult("success");
            result.setData(questionList);
        }
        return result;
    }

    @RequestMapping(path = "/downloadDoc")
    public JsonResult<Object> downloadDoc(@RequestParam Integer quesID, HttpServletRequest request, HttpServletResponse response) {
        result = new JsonResult<>();
        questionList = indexService.getQuesById(quesID);
        try {
            root = String.valueOf(ResourceUtils.getURL("application.properties"));
            produceWordUtil.produceWord(root, questionList, quesID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @PostMapping(path = "/getQuesFromById")
    public JsonResult<Object> getQuesFromById(@RequestParam Integer quesId) {
        result = new JsonResult<>();
        CQList = indexService.getQuesFromById(quesId);
        if (CQList.size() != 0) {
            result.setResult("success");
            result.setData(CQList);
        } else {
            result.setResult("fail");
            result.setData("");
        }
        return result;

    }

    @PostMapping(path = "/uploadAnswer")
    public JsonResult<Object> uploadAnswer(@RequestParam Integer userID, @RequestParam String answer, @RequestParam Integer questionnaireID) {
        result = new JsonResult<>();
        Map<Integer, String> map = JSON.parseObject(answer, HashMap.class);
//        （届时，应该判断questionnaireId是否等于固定值）分析是或存在老年病
        String msg = isExistenceDisease(map);
        Integer value = indexService.setAnswer(userID, map, questionnaireID);
        if (value != 0) {
            result.setResult("success");
        } else {
            result.setResult("fail");
        }
        result.setData(msg);

        return result;
    }

    //    判断是否存在疾病
    private String isExistenceDisease(Map<Integer, String> map) {
        System.out.println("分析中:    " + map);
        TreeNode treeNode = new TreeNode();

        List<String> values = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()
                ) {
            values.add(entry.getValue());
        }

        treeNode.setAnswers(values);

        Test test = new Test();
        String msg = test.statrtMain(treeNode);

        System.out.println("在Controller中的msg:   " + msg);

        return msg;

    }

    @PostMapping(path = "/getHotQues")
    public JsonResult<Object> getHotQues() {
        result = new JsonResult<>();
        List<Integer> quesIDs = new ArrayList<>();
        CQList = indexService.geHotQues();
        if (CQList.size() != 0) {
            result.setResult("success");
            result.setData(CQList);
        } else {
            result.setResult("fail");
            result.setData("");
        }
        return result;
    }

    @PostMapping(path = "/getChartInfo")
    public JsonResult<Object> getChartInfo() {
        result = new JsonResult<>();
        List<UserQuestionnaire> userQuestionnaires = indexService.getChartInfo();

        if (userQuestionnaires.size() != 0) {
            result.setResult("Success");
        } else {
            result.setResult("fail");
        }
        result.setData(userQuestionnaires);

        return result;
    }

    @PostMapping(path = "/getPassword")
    public JsonResult<Object> getPassword(@RequestParam Integer questionnaireId) {
        result = new JsonResult<>();
        String password = indexService.getPassword(questionnaireId);

        result.setData(password);

        return result;
    }

    @PostMapping(path = "/getQuesBySurveyId")
    public JsonResult<Object> getQuests(@RequestParam Integer surveyID) {
        result = new JsonResult<>();
        questionList = indexService.getQuests(surveyID);

        result.setData(questionList);

        return result;
    }

    @PostMapping(path = "/getQuesAnswerById")
    public JsonResult<Object> getQuesAnswerById(@RequestParam Integer quesId) {
        result = new JsonResult<>();
        answerCount = indexService.getQuesAnswerById(quesId);

        result.setData(answerCount);
        return result;
    }


}
