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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = User.class, mappedBy = "courses")
	private Set<User> users = new HashSet<>();

	protected Course() {
	}

	public Course(String name) {
		this.name = name;
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

	public Set<User> getUsers() {
		return users;
	}

	@Override
	public String toString() {
		return String.format("Course[id=%d, Name='%s']", id, name);
	}
}
