package com.jfw.qms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class AnswerCountPK implements Serializable {
    private int id;
    private int quesId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ques_id", nullable = false)
    @Id
    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerCountPK that = (AnswerCountPK) o;

        if (id != that.id) return false;
        if (quesId != that.quesId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quesId;
        return result;
    }
}
