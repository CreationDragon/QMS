package com.jfw.qms.data;

public enum EmFood {
	LIGHT(1, "�嵭"), ORDINARY(1, "һ��"), HEAVY(3, "�ؿ�ζ");

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
