package com.jfw.qms.entity;

import javax.persistence.*;

@Entity
@Table(name = "reply_questionnaire", schema = "qms", catalog = "")
public class ReplyQuestionnaire {
    private int id;
    private Integer quesId;
    private String quesAnswer;
    private Integer questionnaireId;
    private Integer userId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ques_id", nullable = true)
    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    @Basic
    @Column(name = "ques_answer", nullable = true, length = 255)
    public String getQuesAnswer() {
        return quesAnswer;
    }

    public void setQuesAnswer(String quesAnswer) {
        this.quesAnswer = quesAnswer;
    }

    @Basic
    @Column(name = "questionnaire_id", nullable = true)
    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReplyQuestionnaire that = (ReplyQuestionnaire) o;

        if (id != that.id) return false;
        if (quesId != null ? !quesId.equals(that.quesId) : that.quesId != null) return false;
        if (quesAnswer != null ? !quesAnswer.equals(that.quesAnswer) : that.quesAnswer != null) return false;
        if (questionnaireId != null ? !questionnaireId.equals(that.questionnaireId) : that.questionnaireId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (quesId != null ? quesId.hashCode() : 0);
        result = 31 * result + (quesAnswer != null ? quesAnswer.hashCode() : 0);
        result = 31 * result + (questionnaireId != null ? questionnaireId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
