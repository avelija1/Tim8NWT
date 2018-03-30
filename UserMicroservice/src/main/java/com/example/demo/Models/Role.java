package com.example.demo.Models;


import java.util.Set;

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
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
    @Size(min=2, max=30)
	private String name;
	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.EAGER)
	private Set<User> users;
	 protected Role() {}
	public Role(String name)
	{
		this.name=name;
	}
	/*
	public Role(String name, Set<User> users)
	{
		this.name=name;
		this.users = users;
	}*/
	
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
	public Set<User> getUsers() {
		return users;
	}
	/*
	public void setUsers(Set<User> users) {
		this.users = users;
	}*/
	 @Override
	    public String toString() {
	        return String.format(
	                "Role[id=%d, Name='%s']",
	                id, name);
	    }
}
