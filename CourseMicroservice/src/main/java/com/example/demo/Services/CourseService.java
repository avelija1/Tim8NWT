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

@Service("courseService")
@Transactional
public class CourseService implements ICourseService {
	private static final AtomicLong counter = new AtomicLong();
	private static List<Course> courses;
	
	static { 
		courses = populateDummyCourses();
	}
	
	private static List<Course> populateDummyCourses() {
		List<Course> courses = new ArrayList<Course>();
		Course course1 = new Course("KURS1", "KOD", 5.0, "OPIS");
		Course course2 = new Course("KURS2", "KOD2", 6.5, "OPIS2");
		courses.add(course1);
		courses.add(course2);
		
		/*User user1 = new User("Imenko","Prezimenko");
		User user2 = new User("Selma","Glavić");
		
		User user7 = new User("NN","NN",new HashSet<Course>(){{
            add(course1);
            add(course2);
        }});
		
		user1.setCourses(new HashSet<Course>(){{
            add(course1);
            add(course2);
        }});
		
		user2.setCourses(new HashSet<Course>(){{
            add(course1);
            add(course2);
        }});
		
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user7);
		course1.setUsers((Set<User>) users);
		course2.setUsers((Set<User>) users);*/
		
		return courses;
	}
	
	
	@Override
	public Course getCourse(long id) {
		for (Course course : courses) {
			if (course.getId() == id) { 
				return course;
			}
		}
		return null;
	}

	@Override
	public void createCourse(Course course) {
		course.setId(counter.incrementAndGet());
		courses.add(course);
	}

	@Override
	public void editCourse(long id, Course modifiedCourse) {
		int index = courses.indexOf(modifiedCourse);
		courses.set(index, modifiedCourse);
	}

	@Override
	public void deleteCourse(long id) {
		for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext(); ) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
			}
		}
		
	}

	@Override
	public List<Course> getCourses() {
		return courses;
	}

	@Override
	public void ChangeUserEnrollmentOnCourse(Course course, User user) {
		for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext(); ) {
			Course c = iterator.next();
			if (c.getId() == course.getId()) {
				for (Iterator<User> i = c.getUsers().iterator(); i.hasNext(); ) {
					User u = i.next();
					if (u.getCourses().contains(c)) {
						i.remove();
					}
				}
			}
		}
		
	}

}
