package com.example.demo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
    @Size(min=2, max=50)
	private String name;
	
	@Size(max=25)
	private String code;
	
	@DecimalMax("10.0") 
	@DecimalMin("0.5")
	private double ects;
	
	@Size(max=255)
	private String description;
	
	@OneToMany(targetEntity = Activity.class, mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Activity> activities = new HashSet<>();
	
	protected Course() {}
	
	public Course(String name, String code, double ects, String description)
	{
		this.name = name;
		this.code = code;
		this.ects = ects;
		this.description = description;
	}
	
	
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
