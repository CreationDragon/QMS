package com.jfw.qms.data;

public enum EmSleep {
	GOOD(1,"����"),
	ORDINARY(1,"һ��"),
	BAD(3,"�ϲ�");
	
	private final Integer level;
    private final String desc;

	private EmSleep(Integer level, String desc) {
		this.level = level;
		this.desc = desc;
	}

	public Integer getLevel(){return this.level;}

}
