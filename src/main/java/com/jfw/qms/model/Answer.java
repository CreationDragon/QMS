package com.jfw.qms.model;

public class Answer {
    private Integer quesId;
    private String answer;

    public Integer getQuesId() {
        return quesId;
    }

    public void setQuesId(Integer quesId) {
        this.quesId = quesId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "quesId=" + quesId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
