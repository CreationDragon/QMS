package com.jfw.qms.data;

public enum EmFood {
	LIGHT(1, "清淡"), ORDINARY(1, "一般"), HEAVY(3, "重口味");

	private final Integer level;
	private final String desc;

	private EmFood(Integer level, String desc) {
		this.level = level;
		this.desc = desc;
	}

	public Integer getLevel() {
		return this.level;
	}
}
