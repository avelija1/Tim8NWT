package com.example.demo.Models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.util.List;
@Entity
@Table(name = "activity_place")
public class ActivityPlace {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String buildingName;
	private String classRoomName;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "activityPlace", orphanRemoval = true)

	private Set<Activity> activities;

	protected ActivityPlace() {
	}

	public ActivityPlace(String buildingName, String classRoomName) {
		this.buildingName = buildingName;
		this.classRoomName = classRoomName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getClassRoomName() {
		return classRoomName;
	}

	public void setClassRoomName(String classRoomName) {
		this.classRoomName = classRoomName;
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	@Override
	public String toString() {
		return String.format("ActivityPlace[id=%d, BuildingName='%s']", id, buildingName);
	}

	public Set<Activity> getActivities() {
		return activities;
	}

}
