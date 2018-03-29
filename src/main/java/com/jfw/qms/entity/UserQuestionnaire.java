package com.jfw.qms.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_questionnaire", schema = "qms", catalog = "")
@IdClass(UserQuestionnairePK.class)
public class UserQuestionnaire {
    private int questionnaireId;
    private int userId;

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
}
