package com.jfw.qms.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {
    private int questionId;
    private Integer diseaseId;
    private String title;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private Integer questionState;
    private Integer count;

    @Id
    @Column(name = "question_id", nullable = false)
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "disease_id", nullable = true)
    public Integer getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "answer_A", nullable = true, length = 50)
    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    @Basic
    @Column(name = "answer_B", nullable = true, length = 50)
    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    @Basic
    @Column(name = "answer_C", nullable = true, length = 50)
    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    @Basic
    @Column(name = "answer_D", nullable = true, length = 50)
    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionId != question.questionId) return false;
        if (diseaseId != null ? !diseaseId.equals(question.diseaseId) : question.diseaseId != null) return false;
        if (title != null ? !title.equals(question.title) : question.title != null) return false;
        if (answerA != null ? !answerA.equals(question.answerA) : question.answerA != null) return false;
        if (answerB != null ? !answerB.equals(question.answerB) : question.answerB != null) return false;
        if (answerC != null ? !answerC.equals(question.answerC) : question.answerC != null) return false;
        if (answerD != null ? !answerD.equals(question.answerD) : question.answerD != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (diseaseId != null ? diseaseId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (answerA != null ? answerA.hashCode() : 0);
        result = 31 * result + (answerB != null ? answerB.hashCode() : 0);
        result = 31 * result + (answerC != null ? answerC.hashCode() : 0);
        result = 31 * result + (answerD != null ? answerD.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "question_state", nullable = true)
    public Integer getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
