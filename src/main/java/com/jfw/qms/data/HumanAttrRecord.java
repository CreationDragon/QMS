package com.jfw.qms.data;

public class HumanAttrRecord extends BaseRecord {
    private EmAgeLevel age;
    private EmExercise exercise;
    private EmFood food;
    private EmSleep sleep;
	public HumanAttrRecord(EmAgeLevel age, EmExercise exercise, EmFood food, EmSleep sleep, boolean decisionAttr) {
		super(decisionAttr);
		this.age = age;
		this.exercise = exercise;
		this.food = food;
		this.sleep = sleep;
	}
	public EmAgeLevel getAge() {
		return age;
	}
	public void setAge(EmAgeLevel age) {
		this.age = age;
	}
	public EmExercise getExercise() {
		return exercise;
	}
	public void setExercise(EmExercise exercise) {
		this.exercise = exercise;
	}
	public EmFood getFood() {
		return food;
	}
	public void setFood(EmFood food) {
		this.food = food;
	}
	public EmSleep getSleep() {
		return sleep;
	}
	public void setSleep(EmSleep sleep) {
		this.sleep = sleep;
	}
    
    


  

}
