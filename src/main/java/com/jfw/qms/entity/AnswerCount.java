package com.jfw.qms.entity;

import javax.persistence.*;

@Entity
@Table(name = "answer_count", schema = "qms", catalog = "")
@IdClass(AnswerCountPK.class)
public class AnswerCount {
    private int id;
    private int quesId;
    private Integer a;
    private Integer b;
    private Integer c;
    private Integer d;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "ques_id", nullable = false)
    public int getQuesId() {
        return quesId;
    }

    public void setQuesId(int quesId) {
        this.quesId = quesId;
    }

    @Basic
    @Column(name = "A", nullable = true)
    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    @Basic
    @Column(name = "B", nullable = true)
    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Basic
    @Column(name = "C", nullable = true)
    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    @Basic
    @Column(name = "D", nullable = true)
    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerCount that = (AnswerCount) o;

        if (id != that.id) return false;
        if (quesId != that.quesId) return false;
        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;
        if (c != null ? !c.equals(that.c) : that.c != null) return false;
        if (d != null ? !d.equals(that.d) : that.d != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + quesId;
        result = 31 * result + (a != null ? a.hashCode() : 0);
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }
}
