package com.jfw.qms.controller;

import com.alibaba.fastjson.JSON;
import com.jfw.qms.entity.User;
import com.jfw.qms.model.JsonResult;
import com.jfw.qms.model.TableInfo;
import com.jfw.qms.model.ThreeArea;
import com.jfw.qms.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ManagerController {
    private JsonResult result;
    private com.jfw.qms.model.User user;
    private List<com.jfw.qms.model.User> users = new ArrayList<>();
    private TableInfo tableInfo = new TableInfo();
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

    @PostMapping(path = "/admin/getUserInfoById")
    public JsonResult getUserInfoById(@RequestParam String userId) {
        result = new JsonResult();
        user = new com.jfw.qms.model.User();
        user = managerService.getUserInfoById(userId);

        result.setResult("success");
        result.setData(user);
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

}
