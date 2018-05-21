package com.jfw.qms.data;

public enum EmExercise {
	
	OCCASIONAL(3,"ż��"),
	ORDINARY(2,"һ��"),
	OFTEN(1,"����");
	
	private final Integer level;
    private final String desc;
   
    

    private EmExercise(Integer level, String desc) {
		this.level = level;
		this.desc = desc;
	}



	public Integer getLevel(){return this.level;}

}
