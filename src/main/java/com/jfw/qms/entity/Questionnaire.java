package com.jfw.qms.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Questionnaire {
    private int id;
    private Integer questionnaireId;
    private Integer questionId;
    private String questionAnswer;
    private Integer clickFrequency;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "question_id", nullable = true)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "question_answer", nullable = true, length = 10)
    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Questionnaire that = (Questionnaire) o;

        if (id != that.id) return false;
        if (questionnaireId != null ? !questionnaireId.equals(that.questionnaireId) : that.questionnaireId != null)
            return false;
        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null) return false;
        if (questionAnswer != null ? !questionAnswer.equals(that.questionAnswer) : that.questionAnswer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (questionnaireId != null ? questionnaireId.hashCode() : 0);
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (questionAnswer != null ? questionAnswer.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "click_frequency")
    public Integer getClickFrequency() {
        return clickFrequency;
    }

    public void setClickFrequency(Integer clickFrequency) {
        this.clickFrequency = clickFrequency;
    }
}
