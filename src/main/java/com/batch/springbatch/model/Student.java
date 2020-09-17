package com.batch.springbatch.model;

public class Student {

	private String name;
	private String classId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", classId=" + classId + "]";
	}
	
	
}
