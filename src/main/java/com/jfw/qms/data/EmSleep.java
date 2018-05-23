package com.jfw.qms.data;

public enum EmSleep {
	GOOD(1,"较差"),
	ORDINARY(1,"一般"),
	BAD(3,"良好");
	
	private final Integer level;
    private final String desc;

	private EmSleep(Integer level, String desc) {
		this.level = level;
		this.desc = desc;
	}

	public Integer getLevel(){return this.level;}

}
