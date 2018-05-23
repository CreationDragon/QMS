package com.jfw.qms.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_questionnaire", schema = "qms", catalog = "")
@IdClass(UserQuestionnairePK.class)
public class UserQuestionnaire {
    private int questionnaireId;
    private int userId;
    private String questionnaireName;
    private Integer clickFrequency;
    private Integer isEncrypt;
    private String password;

    @Id
    @Column(name = "questionnaire_id", nullable = false)
    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserQuestionnaire that = (UserQuestionnaire) o;

        if (questionnaireId != that.questionnaireId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionnaireId;
        result = 31 * result + userId;
        return result;
    }

    @Basic
    @Column(name = "questionnaire_name", nullable = true, length = 255)
    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    @Basic
    @Column(name = "click_frequency", nullable = true)
    public Integer getClickFrequency() {
        return clickFrequency;
    }

    public void setClickFrequency(Integer clickFrequency) {
        this.clickFrequency = clickFrequency;
    }

    @Basic
    @Column(name = "isEncrypt")
    public Integer getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(Integer isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
