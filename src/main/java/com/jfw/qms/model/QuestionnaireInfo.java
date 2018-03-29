package com.jfw.qms.model;

import com.jfw.qms.model.Question;

import java.util.List;

public class QuestionnaireInfo {
    private Integer code;
    private String msg;
    private int count;
    private List<Question> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Question> getData() {
        return data;
    }

    public void setData(List<Question> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QuestionnaireInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
