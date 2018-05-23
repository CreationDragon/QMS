package com.jfw.qms.data;

public enum EmExercise {
	
	OCCASIONAL(3,"偶尔"),
	ORDINARY(2,"一般"),
	OFTEN(1,"经常");
	
	private final Integer level;
    private final String desc;
   
    

    private EmExercise(Integer level, String desc) {
		this.level = level;
		this.desc = desc;
	}



	public Integer getLevel(){return this.level;}

}
