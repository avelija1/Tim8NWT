package com.example.demo;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
    @Size(min=2, max=30)
	private String name;

	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User user;
	
	//private Long userId;
	
	
	public Task(String name)
	 {
		 this.name=name;
	 }
	
	public Task(String name, User user)
	 {
		 this.name=name;
		 this.user = user;
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
