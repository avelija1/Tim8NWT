package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Activity {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
		@NotNull
	    @Size(min=2, max=50)
	private String name;

	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "activity_type_id")
	private ActivityType activityType;

	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "activity_place_id")
	private ActivityPlace activityPlace;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private Course course;
	
	 protected Activity() {}

	 public Activity(String name, ActivityType at,ActivityPlace ap, Course c)
	 {
		 this.name=name;
		 this.activityType=at;
		 this.activityPlace=ap;
		 this.course=c;
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
	 @Override
	    public String toString() {
	        return String.format(
	                "Activity[id=%d, Name='%s']",
	                id, name);
	    }
	 
	
	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public ActivityPlace getActivityPlace() {
		return activityPlace;
	}

	public void setActivityPlace(ActivityPlace activityPlace) {
		this.activityPlace = activityPlace;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

	 

}
