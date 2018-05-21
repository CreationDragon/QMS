package com.jfw.qms.data;


public enum  EmIncome {

    HIGH(1, "������"),
    MEDIUM(2,"������"),
    LOW(3,"������");

    private final Integer level;
    private final String desc;
    private EmIncome(Integer level, String desc) {this.level = level;this.desc = desc;}

    public Integer getLevel(){return this.level;}

}
