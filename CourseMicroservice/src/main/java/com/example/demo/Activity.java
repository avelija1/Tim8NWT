package com.example.demo;

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

@Entity
@Table(name = "Activity")
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Size(max=255)
	private String name;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
	
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


	 

}
