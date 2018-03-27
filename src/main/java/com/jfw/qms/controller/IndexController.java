package com.jfw.qms.controller;

import com.alibaba.fastjson.JSON;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.model.User;
import com.jfw.qms.model.Area;
import com.jfw.qms.model.JsonResult;
import com.jfw.qms.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    private String msg;
    private HttpSession session;
    private JsonResult result = new JsonResult();
    private User user = new User();

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

        user = indexService.login(userName, userPsw);

        if (user.getUserAuthority() == 1) {
            //将数据存储在Session中
            session.setAttribute("user_name", user.getUserName());
            session.setAttribute("user_psw", user.getUserPsw());
            session.setAttribute("user_authority", user.getUserAuthority());

        } else {
            //将数据存储在Session中
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userPsw", user.getUserPsw());
            session.setAttribute("user_authority", user.getUserAuthority());
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

    @PostMapping(path = "/loginout")
    public JsonResult loginout(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        if (session == null) {
            result.setResult("faile");
            result.setData("faile");
        } else {
            session.removeAttribute("userName");
            session.removeAttribute("userPsw");

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

            result.setResult("success");
            result.setData("success");
        }

        return result;
    }


    @PostMapping(path = "/upload/head")
    public String upload(MultipartFile file) {

        if (null != file) {
            String myFileName = file.getOriginalFilename();// 文件原名称

            File fileDir = new File("D://image/head");
            if (!fileDir.exists()) { //如果不存在 则创建
                fileDir.mkdirs();
            }
            String path = "D://image/head.png";
            File localFile = new File(path);
            try {
                file.transferTo(localFile);
                return path;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件为空");
        }
        return null;

    }

}
