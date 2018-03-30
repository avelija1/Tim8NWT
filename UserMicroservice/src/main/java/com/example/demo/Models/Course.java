package com.example.demo.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Table(name = "course")
@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	/*private String code;
	private double ects;
	private String description;*/
	/*
	@ManyToMany(mappedBy="courses")
	private Set<User> users;*/
	
	@ManyToMany(fetch = FetchType.EAGER,
            targetEntity=User.class,
            mappedBy = "courses")
    private Set<User> users = new HashSet<>();
	
//	private Set<Activity> activities;
	//private Set<User> users;
	 protected Course() {}
		
	 
	 public Course(String name)
	 {
		 this.name = name;
	 }
	 /*
	 public Course(String name, Set<User> users)
		{
			this.name=name;
			this.users = users;
		}*/
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
/*
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
	}*/
	
	@Override
    public String toString() {
        return String.format(
                "Course[id=%d, Name='%s']", id, name);
    }
	public Set<User> getUsers(){
		return users;
	}
	/*
	public void setUsers(Set<User> users) {
		this.users = users;
	}*/
}
