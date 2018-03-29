package com.jfw.qms.repository;

import com.jfw.qms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class IndexRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<AreaCity> areaCityList;
    private List<AreaProvince> areaProvinceList;
    private List<AreaDistrict> areaDistrictList;
    private String msg;
    private User user;
    private List<User> userList;


    public Area getArea(Integer count, String sign) {
        if ("province".equals(sign)) {
            areaProvinceList = jdbcTemplate.query("select * from area_province", new BeanPropertyRowMapper<>(AreaProvince.class));
        } else if (sign.equals("city")) {
            areaCityList = jdbcTemplate.query("select * from qms.area_city WHERE province_id=" + count, new BeanPropertyRowMapper<>(AreaCity.class));
//            areaCityList = jdbcTemplate.query("select * from qms.area_city WHERE city_id=" + count, new BeanPropertyRowMapper<>(AreaCity.class));
        } else if (sign.equals("district")) {
//            count = count - 1;
            areaDistrictList = jdbcTemplate.query("select * from qms.area_district WHERE city_id=" + count, new BeanPropertyRowMapper<>(AreaDistrict.class));
//            areaDistrictList = jdbcTemplate.query("select * from qms.area_district WHERE district_id=" + count, new BeanPropertyRowMapper<>(AreaDistrict.class));
        }
        Area area = new Area();
        area.setProvinceList(areaProvinceList);
        area.setCityList(areaCityList);
        area.setDistrictList(areaDistrictList);

        return area;
    }

    public String registerNumber(com.jfw.qms.model.User user) {
        Integer name = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_name = '" + user.getUserName() + "'", Integer.class);
        Integer email = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_email = '" + user.getUserEmail() + "'", Integer.class);
        Integer phone = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_phone = '" + user.getUserPhone() + "'", Integer.class);

        if (name != 0) {
            msg = "该昵称已被使用";
        } else if (email != 0) {
            msg = "该邮箱已被使用";
        } else if (phone != 0) {
            msg = "该电话已被使用";
        } else {
            int value = jdbcTemplate.update("INSERT INTO USER(user_name, user_psw, user_gener, user_phone, user_email,user_address_detail,user_province, user_city, user_district) VALUES(?,?,?,?,?,?,?,?,?) ",
                    new Object[]{
                            user.getUserName(), user.getUserPsw(), user.getUserGener(),
                            user.getUserPhone(), user.getUserEmail(), user.getUserAddressDetail(),
//                            user.getProvince(), user.getCity(), user.getDistrict()
                            user.getProvince(), user.getCity(), user.getDistrict()
                    });
            System.out.println("插入数据后返回的数是:     " + value);
            msg = "注册成功";
        }
        return msg;
    }

    public User getUserInfo() {
        return user;
    }

    public User login(String userName, String userPsw) {
        user = new User();
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM USER WHERE user_name= ? AND user_psw= ?", new Object[]{userName, userPsw}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            return user;
        }
        return user;
    }

    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        com.jfw.qms.entity.AreaProvince areaProvince = jdbcTemplate.queryForObject("SELECT * FROM qms.area_province WHERE province_id= ? ", new Object[]{provinceID}, new BeanPropertyRowMapper<>(com.jfw.qms.entity.AreaProvince.class));
        com.jfw.qms.entity.AreaCity areaCity = jdbcTemplate.queryForObject("SELECT * FROM qms.area_city WHERE city_id= ? ", new Object[]{cityID}, new BeanPropertyRowMapper<>(com.jfw.qms.entity.AreaCity.class));
        com.jfw.qms.entity.AreaDistrict areaDistrict = jdbcTemplate.queryForObject("SELECT * FROM qms.area_district WHERE district_id= ? ", new Object[]{districtID}, new BeanPropertyRowMapper<>(com.jfw.qms.entity.AreaDistrict.class));

        ThreeArea threeArea = new ThreeArea();
        threeArea.setAreaProvince(areaProvince);
        threeArea.setAreaCity(areaCity);
        threeArea.setAreaDistrict(areaDistrict);

        return threeArea;
    }
}
