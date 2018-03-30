package com.example.demo.Models;

import java.util.Set;

//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "activity_type")
public class ActivityType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
    @Size(min=2, max=50)
	private String name;

	@OneToMany(targetEntity = Activity.class, mappedBy = "activityType", fetch = FetchType.EAGER)
	private Set<Activity> activities;

	protected ActivityType() {
	}

	public ActivityType(String name) {

		this.name = name;
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
		return String.format("ActivityPlace[id=%d, Name='%s']", id, name);
	}
	
	public Set<Activity> getActivities() {
		return activities;
	}
	/*
	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}*/
}
