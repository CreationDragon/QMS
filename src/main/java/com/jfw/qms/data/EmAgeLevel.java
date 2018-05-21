package com.jfw.qms.data;

public enum EmAgeLevel {

    SENIOR(1, "������"),
    MIDDLE_AGED(2,"������"),
    YOUTH(3,"������");

    private final Integer level;
    private final String desc;
    private EmAgeLevel(Integer level, String desc) {this.level = level;this.desc = desc;}

    public Integer getLevel(){return this.level;}
}
