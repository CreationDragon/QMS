package com.jfw.qms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserQuestionnairePK implements Serializable {
    private int questionnaireId;
    private int userId;

    @Column(name = "questionnaire_id", nullable = false)
    @Id
    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Column(name = "user_id", nullable = false)
    @Id
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

        UserQuestionnairePK that = (UserQuestionnairePK) o;

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
