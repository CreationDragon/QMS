package com.jfw.qms.repository;

import com.jfw.qms.entity.AreaCity;
import com.jfw.qms.entity.AreaDistrict;
import com.jfw.qms.entity.AreaProvince;
import com.jfw.qms.entity.User;
import com.jfw.qms.model.ThreeArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ManagerRepository {
    private List<User> users = new ArrayList<>();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUser() {
        users = jdbcTemplate.query("SELECT * FROM USER WHERE user_state=0", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserGener(resultSet.getString("user_gener"));
                user.setUserPhone(resultSet.getString("user_phone"));
                user.setUserAuthority(resultSet.getInt("user_authority"));
                user.setUserAddressDetail(resultSet.getString("user_address_detail"));
                user.setUserProvince(resultSet.getInt("user_province"));
                user.setUserCity(resultSet.getInt("user_city"));
                user.setUserDistrict(resultSet.getInt("user_district"));
                user.setUserEmail(resultSet.getString("user_email"));
                return user;
            }
        });
        return users;
    }

    public String getCityById(Integer userCityId) {

        String city = jdbcTemplate.queryForObject("SELECT  city_name FROM area_city WHERE city_id= " + userCityId, String.class);

        return city;
    }

    public String getProvinceById(Integer userProvinceId) {
        String province = jdbcTemplate.queryForObject("SELECT  province_name FROM qms.area_province WHERE province_id= " + userProvinceId, String.class);

        return province;
    }

    public String getDistrictById(Integer userDistrictId) {
        String district = jdbcTemplate.queryForObject("SELECT  district_name FROM qms.area_district WHERE district_id= " + userDistrictId, String.class);

        return district;
    }

    public Integer deleteUser(Integer userid) {

        Integer result = jdbcTemplate.update("UPDATE USER SET user_state=1 WHERE user_id=?", new Object[]{userid});
        return result;
    }

    public com.jfw.qms.model.User getUserInfoById(Integer userid) {
        com.jfw.qms.model.User user = new com.jfw.qms.model.User();
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM USER WHERE user_id= ? ", new Object[]{userid}, new BeanPropertyRowMapper<>(com.jfw.qms.model.User.class));
        } catch (Exception e) {
            return user;
        }
        return user;
    }

    public ThreeArea getAreaById(String provinceID, String cityID, String districtID) {
        AreaProvince areaProvince = jdbcTemplate.queryForObject("SELECT * FROM qms.area_province WHERE province_id= ? ", new Object[]{provinceID}, new BeanPropertyRowMapper<>(AreaProvince.class));
        AreaCity areaCity = jdbcTemplate.queryForObject("SELECT * FROM qms.area_city WHERE city_id= ? ", new Object[]{cityID}, new BeanPropertyRowMapper<>(AreaCity.class));
        AreaDistrict areaDistrict = jdbcTemplate.queryForObject("SELECT * FROM qms.area_district WHERE district_id= ? ", new Object[]{districtID}, new BeanPropertyRowMapper<>(AreaDistrict.class));

        ThreeArea threeArea = new ThreeArea();
        threeArea.setAreaProvince(areaProvince);
        threeArea.setAreaCity(areaCity);
        threeArea.setAreaDistrict(areaDistrict);

        return threeArea;
    }
}
