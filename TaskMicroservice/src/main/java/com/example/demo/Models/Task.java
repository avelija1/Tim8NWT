package com.example.demo.Models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 2, max = 30)
	private String name;

	@NotNull
	@Size(min = 5, max = 100)
	private String notes;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/YY")
	private Date date;
	
	@NotNull
	private boolean status;	

	@ManyToOne
	private User user;

	protected Task() {
	}

	public Task(String name, String notes, Date date, boolean status, User user) {
		this.name = name;
		this.notes = notes;
		this.date = date;
		this.user = user;
		this.status = status;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String status() {
		if (status == false) {
			return "Undone";
		} else {
			return "Done";
		}
	}

	@Override
	public String toString() {
		return String.format("Task[id=%d, Name='%s', Date='%td/%tm/%tY', User='%s', Status='%s']", id, name, date, date,
				date, user.toString(), status());
	}

}
