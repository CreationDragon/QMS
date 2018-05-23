package com.jfw.qms.model;

import com.jfw.qms.entity.AnswerCount;
import com.jfw.qms.entity.Question;

public class Ensemble {
    private Question question;
    private AnswerCount answerCount;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerCount getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(AnswerCount answerCount) {
        this.answerCount = answerCount;
    }
}
