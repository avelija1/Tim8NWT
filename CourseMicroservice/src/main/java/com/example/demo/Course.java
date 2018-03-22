package com.example.demo;




public class Course {
	private long id;
	private String name;
	private String code;
	private double ects;
	private String description;
//	private Set<Activity> activities;
	//private Set<User> users;
	 protected Course() {}
	public long getId() {
		return this.id;
	}

	public void setId(long value) {
		this.id = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}

	public String getCode() {
		return this.code;
	}
	
	public void setCode(String value) {
		this.code = value;
	}

	public double getEcts() {
		return this.ects;
	}

	public void setEcts(double value) {
		this.ects = value;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	
}
