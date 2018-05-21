package com.jfw.qms.data;


public enum  EmCreditRate {

    EXCELLENT(1, "����"),
    FAIR(2,"����");

    private final Integer level;
    private final String desc;
    private EmCreditRate(Integer level, String desc) {this.level = level;this.desc = desc;}

    public Integer getLevel(){return this.level;}

}