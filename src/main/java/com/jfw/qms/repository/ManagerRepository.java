package com.jfw.qms.repository;

import com.jfw.qms.entity.*;
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
    private List<Question> questions = new ArrayList<>();

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

    public String editUser(com.jfw.qms.model.User user, Integer userid) {
        String msg = null;
        Integer name = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_name = '" + user.getUserName() + "'", Integer.class);
        Integer email = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_email = '" + user.getUserEmail() + "'", Integer.class);
        Integer phone = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER WHERE user_phone = '" + user.getUserPhone() + "'", Integer.class);

//        if (name != 0) {
//            msg = "该昵称已被使用";
//        } else if (email != 0) {
//            msg = "该邮箱已被使用";
//        } else if (phone != 0) {
//            msg = "该电话已被使用";
//        } else {
        int value = jdbcTemplate.update("UPDATE USER SET user_name=?, user_psw=?, user_gener=?, user_phone=?, user_email=?,user_address_detail=?,user_province=?, user_city=?, user_district=? WHERE user_id=?",
                new Object[]{
                        user.getUserName(), user.getUserPsw(), user.getUserGener(),
                        user.getUserPhone(), user.getUserEmail(), user.getUserAddressDetail(),
//                            user.getProvince(), user.getCity(), user.getDistrict()
                        user.getProvince(), user.getCity(), user.getDistrict(), userid
                });
        System.out.println("插入数据后返回的数是:     " + value);
        msg = "修改成功";
//        }
        return msg;
    }

    public List<Question> getQuestionnaire() {
        questions = new ArrayList<>();

        questions = jdbcTemplate.query("SELECT * FROM question WHERE  question_state = 0", new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet resultSet, int i) throws SQLException {
                Question question = new Question();
                question.setQuestionId(resultSet.getInt("question_id"));
                question.setTitle(resultSet.getString("title"));
                question.setAnswerA(resultSet.getString("answer_A"));
                question.setAnswerB(resultSet.getString("answer_B"));
                question.setAnswerC(resultSet.getString("answer_C"));
                question.setAnswerD(resultSet.getString("answer_D"));

                return question;
            }
        });


        return questions;
    }

    public Integer deleteQues(Integer quesid) {
        Integer result = jdbcTemplate.update("UPDATE qms.question SET question_state=1 WHERE question_id=?", new Object[]{quesid});
        return result;
    }

    public Question getQuestionInfoById(Integer quesid) {
        Question question = new Question();
        try {
            question = jdbcTemplate.queryForObject("SELECT * FROM qms.question WHERE question_id= ? ", new Object[]{quesid}, new BeanPropertyRowMapper<>(Question.class));
        } catch (Exception e) {
            return question;
        }
        return question;
    }

    public String editQues(String title, String answerA, String answerB, String answerC, String answerD, Integer quesid) {
        String msg = null;
        int value = jdbcTemplate.update("UPDATE qms.question SET qms.question.title=?, qms.question.answer_A=?, qms.question.answer_B=?," +
                        " qms.question.answer_C=?, qms.question.answer_D=? WHERE qms.question.question_id=?",
                new Object[]{
                        title, answerA, answerB, answerC, answerD, quesid
                });
        System.out.println("插入数据后返回的数是:     " + value);
        msg = "修改成功";

        return msg;
    }
}
