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


    public Area getArea(Integer count, String sign) {
        if ("province".equals(sign)) {
            areaProvinceList = jdbcTemplate.query("select * from area_province", new BeanPropertyRowMapper<>(AreaProvince.class));
        } else if (sign.equals("city")) {
            areaCityList = jdbcTemplate.query("select * from qms.area_city WHERE province_id=" + count, new BeanPropertyRowMapper<>(AreaCity.class));
        } else if (sign.equals("district")) {
//            count = count - 1;
            areaDistrictList = jdbcTemplate.query("select * from qms.area_district WHERE city_id=" + count, new BeanPropertyRowMapper<>(AreaDistrict.class));
        }
        Area area = new Area();
        area.setProvinceList(areaProvinceList);
        area.setCityList(areaCityList);
        area.setDistrictList(areaDistrictList);

        return area;
    }

    public String registerNumber(User user) {
        Integer name = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_name = '"+user.getUserName()+"'",Integer.class);
        Integer email = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_email = '"+user.getUserEmail()+"'",Integer.class);
        Integer phone = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_phone = '"+user.getUserPhone()+"'",Integer.class);

        if(name!=0){
            msg="该昵称已被使用";
        }else if(email!=0){
            msg="该邮箱已被使用";
        }else if(phone!=0){
            msg="该电话已被使用";
        }else{
        int value = jdbcTemplate.update("INSERT INTO USER(user_name, user_psw, user_gener, user_phone, user_email,user_address_detail,user_province, user_city, user_district) VALUES(?,?,?,?,?,?,?,?,?) ",
                new Object[]{
                user.getUserName(),user.getUserPsw(),user.getUserGener(),
                user.getUserPhone(),user.getUserEmail(),user.getUserAddressDetail(),
                user.getProvince(),user.getCity(),user.getDistrict()
        });
        System.out.println("插入数据后返回的数是:     "+value);
        msg="注册成功";
        }
        return msg;
    }
}
