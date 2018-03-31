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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
//import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "user")

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
    @Size(min=2, max=30)
	private String lastName;
	
	@NotNull
    @Size(min=2, max=30)
	private String firstName;
	
    @Size(min=2, max=30)
	private String userName;
    
    @NotEmpty 
    @Email
	private String email;
    
    @NotNull
    @NotEmpty 
	private String passwordHash;
    
	@NotNull
	@Min(1)
	@Max(5)
	private int year;
	
	@NotNull
	@Min(1)
	@Max(6)
	private int semester;
	
	@ManyToOne(cascade=CascadeType.MERGE, targetEntity=Role.class,fetch = FetchType.EAGER)
   // @JoinColumn(name = "role_id")
	private Role role;
	/*
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="UserLecture", joinColumns=@JoinColumn(name="UserID", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="CourseID", referencedColumnName="id"))
	private Set<Course> courses;
	
	*/
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            },
            targetEntity=Course.class)
	@JoinTable(name = "user_courses",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private Set<Course> courses = new HashSet<>();
	
	@OneToMany(targetEntity = Task.class, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Task> tasks = new HashSet<>();
	
	//private List<Task> tasks;
	 
	protected User() {}
	
	public User(String firstName,String lastName, String userName, String email, String passwordHash, int year, int semester)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.email=email;
		this.passwordHash=passwordHash;
		this.year=year;
		this.semester=semester;
	}
	
	public User(long id,String firstName,String lastName, String userName, String email, String passwordHash, Role role, int year, int semester)
	{
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.email=email;
		this.passwordHash=passwordHash;
		this.role=role;
		this.year=year;
		this.semester=semester;
	}
	
	public User(String firstName,String lastName, String userName, String email, String passwordHash, Role role, int year, int semester)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.email=email;
		this.passwordHash=passwordHash;
		this.role=role;
		this.year=year;
		this.semester=semester;
	}
	
	public User(String firstName,String lastName, String userName, String email, String passwordHash, Role role, int year, int semester, Set<Course> courses)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.email=email;
		this.passwordHash=passwordHash;
		this.role=role;
		this.year=year;
		this.semester=semester;
		this.courses = courses;
	}
	
	public User(String firstName,String lastName, String userName, String email, String passwordHash, int year, int semester, Set<Course> courses)
	{
		this.firstName=firstName;
		this.lastName=lastName;
		this.userName=userName;
		this.email=email;
		this.passwordHash=passwordHash;
		this.year=year;
		this.semester=semester;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override 
	public String toString() {
		return String.format("User[id=%d, last name='%s', first name='%s']", id, lastName, firstName);
	}

	public Set<Course> getCourses(){
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public Set<Task> getTasks(){
		return tasks;
	}
	/*
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}*/
}
