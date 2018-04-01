package com.example.demo.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Interfaces.ICourseService;
import com.example.demo.Models.Course;
import com.example.demo.Models.User;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.UserRepository;

@Service("courseService")
@Transactional
public class CourseService implements ICourseService {
	private static final AtomicLong counter = new AtomicLong();
	
	private CourseRepository courseRepository;
	private UserRepository userRepository;
	
	public CourseService (CourseRepository cr, UserRepository ur) {
		this.courseRepository = cr;
		this.userRepository = ur;
	}
	
	private static List<Course> courses;
	
	
	@Override
	public Course getCourse(long id) {
		courses = (List<Course>)courseRepository.findAll();
		for (Course course : courses) {
			if (course.getId() == id) { 
				return course;
			}
		}
		return null;
	}

	@Override
	public void createCourse(Course course) {
		//course.setId(counter.incrementAndGet());
		courseRepository.save(course);
	}

	@Override
	public void editCourse(long id, Course modifiedCourse) {
		Course course = courseRepository.findOne(id);
		
		course.setCode(modifiedCourse.getCode());
		course.setDescription(modifiedCourse.getDescription());
		course.setEcts(modifiedCourse.getEcts());
		course.setName(modifiedCourse.getName());
		course.setUsers(modifiedCourse.getUsers());
		course.setActivities(modifiedCourse.getActivities());
		
		courseRepository.save(course);
	}

	@Override
	public void deleteCourse(long id) {
		for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext(); ) {
			Course course = iterator.next();
			if (course.getId() == id) {
				courseRepository.delete(id);
			}
		}
		
	}

	@Override
	public List<Course> getCourses() {
		return (List<Course>)courseRepository.findAll();
	}

	@Override
	public void ChangeUserEnrollmentOnCourse(Course modifiedCourse, User modifiedUser) {
		Course course = courseRepository.findOne(modifiedCourse.getId());
		
		Set<User> modifiedUsers = course.getUsers();
		modifiedUsers.remove(modifiedUser);
		course.setUsers(modifiedUsers);
		
		courseRepository.save(course);
		
		
		/*for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext(); ) {
			Course c = iterator.next();
			if (c.getId() == course.getId()) {
				if (c.getUsers().contains(user)) {
					c.getUsers().remove(user);
				}
				for (Iterator<User> i = c.getUsers().iterator(); i.hasNext(); ) {
					User u = i.next();
					if (u.getCourses().contains(c)) {
						i.remove();
					}
				}
			}
		}*/
		
	}

}
