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
	private static List<Course> courses;
	
	
	static { 
		courses = populateDummyCourses();
	}
	
	private static List<Course> populateDummyCourses() {
		List<Course> courses = new ArrayList<Course>();
				
		User user1 = new User("Imenko","Prezimenko");
		User user2 = new User("Selma","Glavić");
		User user3 = new User("Admin","Admin");
		User user4 = new User("Almedin","Velija");
		User user5 = new User("Lejla","Bajgorić");
		User user6 = new User("Amer","Kodžaga");
		
		Course course1 = new Course("KURS1", "KOD", 5.0, "OPIS");
		Course course2 = new Course("KURS2", "KOD2", 6.5, "OPIS2");
		Course course3 = new Course("KURS3", "KOD3", 9.0, "OPIS3");
		Course course4 = new Course("KURS4", "KOD4", 6.0, "OPIS4");
		Course course5 = new Course("KURS5", "KOD5", 8.0, "OPIS5");
		Course course6 = new Course("KURS6", "KOD6", 6.0, "OPIS6");
		
		User user7 = new User("NN","NN",new HashSet<Course>(){{
            add(course1);
            add(course2);
        }});
		
		user1.setCourses(new HashSet<Course>(){{
            add(course1);
            add(course2);
        }});
		
		user4.setCourses(new HashSet<Course>(){{
            add(course5);
            add(course6);
        }});
		
		Set users = new HashSet<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		//course1.setUsers(users);
		
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);
		courses.add(course4);
		courses.add(course5);
		courses.add(course6);
		
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
				if (c.getUsers().contains(user)) {
					c.getUsers().remove(user);
				}
				/*for (Iterator<User> i = c.getUsers().iterator(); i.hasNext(); ) {
					User u = i.next();
					if (u.getCourses().contains(c)) {
						i.remove();
					}
				}*/
			}
		}
		
	}

}
