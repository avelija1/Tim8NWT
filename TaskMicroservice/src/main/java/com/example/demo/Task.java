package com.example.demo;
import java.util.Date;

public class Task {
private Long id;
private String name;
private String notes;
private Date date;
private User user;

private Long userId;
private boolean status;

protected Task() {}
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
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}

}
