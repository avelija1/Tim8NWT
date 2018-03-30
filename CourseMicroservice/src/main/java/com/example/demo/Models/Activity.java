package com.example.demo.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.demo.Models.Course;

@Entity
@Table(name = "Activity")
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Size(max=255)
	private String name;
	
	@ManyToOne(cascade=CascadeType.MERGE, targetEntity=Course.class)
	   // @JoinColumn(name = "course_id")
		private Course course;
	
	protected Activity()
	{
		
	}
	
	//Mislim da konstruktor neće trebati kad se uspostavi mogućnost očitavanja redova
	//tabela u drugim mikroservisima
	public Activity(String name)
	 {
		 this.name=name;
	 }
	
	public Activity(String name, Course course)
	 {
		 this.name=name;
		 this.course = course;
	 }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	 

}
