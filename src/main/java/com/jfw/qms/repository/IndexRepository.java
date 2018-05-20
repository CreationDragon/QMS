package com.jfw.qms.repository;

import com.jfw.qms.entity.AnswerCount;
import com.jfw.qms.entity.Message;
import com.jfw.qms.entity.Question;
import com.jfw.qms.entity.UserQuestionnaire;
import com.jfw.qms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class IndexRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String msg;
    private User user;
    private Question question = new Question();
    private List<AreaCity> areaCityList;
    private List<AreaProvince> areaProvinceList;
    private List<AreaDistrict> areaDistrictList;
    private List<User> userList;
    private AnswerCount answerCount = new AnswerCount();
    private List<CustomQuestionnaire> CQList = new ArrayList<>();
    private List<com.jfw.qms.entity.Question> questionList = new ArrayList<>();

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

    public User getUserInfo(Integer userID) {
        user = new User();
        try {
            user = jdbcTemplate.queryForObject("SELECT * FROM USER WHERE user_id=?", new Object[]{userID}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            return user;
        }
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

    public List<com.jfw.qms.entity.Question> quesSearch(String keyword) {
        questionList = jdbcTemplate.query("SELECT * FROM question WHERE title LIKE '%" + keyword + "%'", new BeanPropertyRowMapper<>(com.jfw.qms.entity.Question.class));
        return questionList;
    }

    public List<User> getAdminInfo() {
        userList = jdbcTemplate.query("SELECT * FROM USER WHERE user_authority = '1'", new BeanPropertyRowMapper<>(User.class));

        return userList;
    }

    public int putMessage(Message data, Integer userID) {
        int value = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM message WHERE user_id=? AND message_content=? AND admin_id=?",
                new Object[]{userID, data.getMessageContent(), data.getAdminId()}, Integer.class);
        if (value == 0) {
            value = jdbcTemplate.update("INSERT INTO message(message_content, admin_id,user_id) VALUES(?,?,?) ",
                    new Object[]{
                            data.getMessageContent(), data.getAdminId(), userID
                    });
        } else {
            value = -1;
        }
        return value;
    }

    public List<CustomQuestionnaire> getAllQues() {
        CQList = jdbcTemplate.query("SELECT questionnaire_id,questionnaire_name,isEncrypt FROM user_questionnaire ", new BeanPropertyRowMapper<>(CustomQuestionnaire.class));
        return CQList;

    }

    public List<com.jfw.qms.entity.Question> getQuesById(Integer quesnaireID) {
        List<Integer> quesID = jdbcTemplate.queryForList("SELECT question_id FROM questionnaire WHERE questionnaire_id=" + quesnaireID, Integer.class);

        questionList = new ArrayList<>();

        for (Integer id : quesID
                ) {
            question = new Question();
            question = jdbcTemplate.queryForObject("SELECT * FROM question WHERE question_id=" + id, new BeanPropertyRowMapper<>(com.jfw.qms.entity.Question.class));
            questionList.add(question);
        }

        return questionList;
    }

    public List<CustomQuestionnaire> getQuesFromById(Integer quesId) {
        CQList = new ArrayList<>();
        List<Integer> quesIDList = jdbcTemplate.queryForList("SELECT questionnaire_id FROM questionnaire WHERE question_id = ? ", new Object[]{quesId}, Integer.class);
        for (Integer questionnaireId : quesIDList
                ) {
            CustomQuestionnaire customQuestionnaire = new CustomQuestionnaire();

            customQuestionnaire = jdbcTemplate.queryForObject("SELECT questionnaire_id,questionnaire_name FROM user_questionnaire WHERE questionnaire_id = ?", new Object[]{questionnaireId}, new BeanPropertyRowMapper<>(CustomQuestionnaire.class));

            CQList.add(customQuestionnaire);
        }
        return CQList;
    }

    public Integer setAnswer(Integer userID, Map<Integer, String> map, Integer questionnaireID) {
        Integer value = null;
        Integer res = null;
        System.out.println(userID + "----" + map);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            res = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM reply_questionnaire WHERE ques_id = ? AND questionnaire_id = ?",
                    new Object[]{entry.getKey(), questionnaireID}, Integer.class);
            if (res != 0) {
                value = jdbcTemplate.update("UPDATE reply_questionnaire SET ques_answer= ? WHERE ques_id = ? AND questionnaire_id = ? AND user_id=?",
                        new Object[]{entry.getValue(), entry.getKey(), questionnaireID, userID});
            } else {
                value = jdbcTemplate.update("INSERT INTO reply_questionnaire(questionnaire_id, ques_id, ques_answer,user_id)VALUE (?,?,?,?)",
                        new Object[]{questionnaireID, entry.getKey(), entry.getValue(), userID});
            }

            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM answer_count WHERE ques_id=?", new Object[]{entry.getKey()}, Integer.class);

            String var = entry.getValue();
            if (count != 0) {
                String sql = "UPDATE answer_count SET " + var + "=" + var + "+1 WHERE ques_id=" + entry.getKey();
                jdbcTemplate.update(sql);
            } else {
                String sql = "INSERT INTO answer_count(ques_id," + var + ")VALUE(" + entry.getKey() + ",1);";
                jdbcTemplate.update(sql);
            }

        }

        if (value != 0) {
            value = jdbcTemplate.update("UPDATE user_questionnaire SET click_frequency = click_frequency+1 WHERE questionnaire_id =?",
                    new Object[]{questionnaireID});
        } else {
            value = 0;
        }

        return value;
    }

    public List<CustomQuestionnaire> getHotQues() {
        CQList = jdbcTemplate.query("SELECT questionnaire_id,questionnaire_name,isEncrypt FROM user_questionnaire ORDER BY click_frequency DESC LIMIT 2 ", new BeanPropertyRowMapper<>(CustomQuestionnaire.class));
        return CQList;
    }

    public List<UserQuestionnaire> getChartInfo() {
        List<UserQuestionnaire> userQuestionnaires = jdbcTemplate.query("SELECT * FROM user_questionnaire ORDER BY click_frequency ASC", new BeanPropertyRowMapper<>(UserQuestionnaire.class));
        return userQuestionnaires;
    }

    public void updateUserHead(String myFileName, Integer userId) {
        jdbcTemplate.update("UPDATE qms.user SET user_head=? WHERE user_id=?", new Object[]{myFileName, userId});
    }

    public String getPassword(Integer questionnaireId) {
        String password = jdbcTemplate.queryForObject("SELECT  password FROM user_questionnaire WHERE questionnaire_id=" + questionnaireId, String.class);
        return password;
    }

    public Integer getRegistersId() {
        Integer userId = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM qms.user", Integer.class);
        return userId;
    }

    public List<Question> getQuests(Integer surveyID) {
        questionList = new ArrayList<>();
        questionList = jdbcTemplate.query("SELECT * FROM questionnaire WHERE questionnaire_id=" + surveyID, new BeanPropertyRowMapper<>(Question.class));
        return questionList;
    }

    public AnswerCount getQuesAnswerById(Integer quesId) {
        answerCount = new AnswerCount();
        answerCount = jdbcTemplate.queryForObject("SELECT * FROM answer_count WHERE ques_id=" + quesId, new BeanPropertyRowMapper<>(AnswerCount.class));
        return answerCount;
    }
}
