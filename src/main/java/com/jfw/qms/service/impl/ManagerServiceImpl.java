package com.jfw.qms.service.impl;

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
}
