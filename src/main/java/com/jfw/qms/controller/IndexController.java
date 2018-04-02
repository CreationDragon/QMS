package com.jfw.qms.controller;

import com.alibaba.fastjson.JSON;
import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.model.Area;
import com.jfw.qms.model.JsonResult;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;
import com.jfw.qms.service.IndexService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    private String msg;
    private HttpSession session;
    private JsonResult result = new JsonResult();
    private User user = new User();
    private List<Question> questionList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private String root = null;

    @RequestMapping(path = "/index")
    public JsonResult getIndex(HttpServletRequest request, Model model) {

        String userName = (String) request.getSession().getAttribute("userName");
        String userPsw = (String) request.getSession().getAttribute("userPsw");

        user.setUserName(userName);
        user.setUserPsw(userPsw);


        result.setResult("success");
        result.setData(user);

        model.addAttribute("data", user);

        return result;
    }

    @RequestMapping(path = "/admin/index")
    public JsonResult getAdminIndex(HttpServletRequest request, Model model) {

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
    public JsonResult getUserInfoById(@RequestParam String provinceID, @RequestParam String cityID, @RequestParam String districtID) {
        ThreeArea threeArea = new ThreeArea();
        threeArea = indexService.getAreaById(provinceID, cityID, districtID);

        result.setResult("success");
        result.setData(threeArea);
        return result;

    }

    @GetMapping(path = "/area")
    public JsonResult getArea(@RequestParam String id, @RequestParam String sign) {
        Area area = indexService.getArea(id, sign);
        JsonResult result = new JsonResult();
        result.setResult("success");
        result.setData(area);
        return result;
    }

    @PostMapping(path = "/register")
    public JsonResult registerNumber(@RequestParam String register_data, HttpServletRequest request, HttpServletResponse response) {
        com.jfw.qms.model.User user = JSON.parseObject(register_data, com.jfw.qms.model.User.class);
        System.out.println(user);


        msg = indexService.registerNumber(user);

        JsonResult result = new JsonResult();
        if (msg.equals("注册成功")) {
            result.setResult("success");
            //获取Session
            session = request.getSession();
            //将数据存储在Session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userPsw", user.getUserPsw());
            session.setAttribute("user_id", user.getUserId());
        } else {
            result.setResult("fail");
        }
        result.setData(msg);
        return result;
    }

    @PostMapping(path = "/getInfo")
    public JsonResult getUserInfo() {
        user = indexService.getUserInfo();

        result.setResult("success");
        result.setData(user);
        return result;
    }

    @PostMapping(path = "/login")
    public JsonResult Login(HttpServletRequest request, @RequestParam String userName, @RequestParam String userPsw) {

        session = request.getSession();
        user = new User();
        user = indexService.login(userName, userPsw);

        if (user.getUserAuthority() == 1) {
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
    public JsonResult modifyUserInfo(@RequestParam String userID) {
        result = new JsonResult();

        return result;
    }


    @PostMapping(path = "/loginout")
    public JsonResult loginout(HttpServletRequest request, HttpServletResponse response) {
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
    public JsonResult adminLoginout(HttpServletRequest request, HttpServletResponse response) {
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
    public String upload(MultipartFile file, @RequestParam String userID) {

        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称
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
                return path;
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
        return "success";

    }

    @PostMapping(path = "/quesSearch")
    public JsonResult quesSearch(@RequestParam String keyword) {
        result = new JsonResult();
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
    public JsonResult getAdminInfo() {
        result = new JsonResult();
        userList = indexService.getAdminInfo();
        result.setResult("success");
        result.setData(userList);

        return result;
    }

    @PostMapping(path = "/putMessage")
    public JsonResult putMessage(@RequestParam String data, @RequestParam Integer userID) {
        Message message = JSON.parseObject(data, Message.class);
        result = new JsonResult();

        int count = indexService.putMessage(message, userID);

        result.setResult("success");
        if (count > 0) {
            result.setResult("success");
            result.setData("留言成功");
        } else {
            result.setResult("faile");
            result.setData("留言失败");
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
    public JsonResult getAllQues() {
        result = new JsonResult();
        List<Integer> quesIDs = new ArrayList<>();
        quesIDs = indexService.getAllQues();
        if (quesIDs.size() != 0) {
            result.setResult("success");
            result.setData(quesIDs);
        } else {
            result.setResult("fail");
            result.setData("");
        }
        return result;
    }

    @PostMapping(path = "/getQuesById")
    public JsonResult getQuesById(@RequestParam Integer quesID) {
        result = new JsonResult();
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
    public JsonResult downloadDoc(@RequestParam Integer quesID, HttpServletRequest request, HttpServletResponse response) {
        result = new JsonResult();
        questionList = indexService.getQuesById(quesID);
        try {
            root = String.valueOf(ResourceUtils.getURL("application.properties"));
            produceWordUtil.produceWord(root, questionList, quesID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
