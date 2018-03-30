package com.example.demo;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Models.Activity;
import com.example.demo.Models.Course;
import com.example.demo.Models.User;
import com.example.demo.Repositories.ActivityRepository;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.UserRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(CourseRepository courseRepository, 
			UserRepository userRepository,
			ActivityRepository activityRepository) {
		return (args) -> {
		
			User user1 = new User("Imenko","Prezimenko");
			User savedUser1 = userRepository.save(user1);
			User user2 = new User("Selma","Glavić");
			User savedUser2 = userRepository.save(user2);
			User user3 = new User("Admin","Admin");
			User savedUser3 = userRepository.save(user3);
			User user4 = new User("Almedin","Velija");
			User savedUser4 = userRepository.save(user4);
			User user5 = new User("Lejla","Bajgorić");
			User savedUser5 = userRepository.save(user5);
			User user6 = new User("Amer","Kodžaga");
			User savedUser6 = userRepository.save(user6);
			
			Course course1 = new Course("KURS1", "KOD", 5.0, "OPIS");
			Course course2 = new Course("KURS2", "KOD2", 6.5, "OPIS2");
			Course course3 = new Course("KURS3", "KOD3", 9.0, "OPIS3");
			Course course4 = new Course("KURS4", "KOD4", 6.0, "OPIS4");
			Course course5 = new Course("KURS5", "KOD5", 8.0, "OPIS5");
			Course course6 = new Course("KURS6", "KOD6", 6.0, "OPIS6");
			Course savedCourse1 = courseRepository.save(course1);
			Course savedCourse2 = courseRepository.save(course2);
			Course savedCourse3 = courseRepository.save(course3);
			Course savedCourse4 = courseRepository.save(course4);
			Course savedCourse5 = courseRepository.save(course5);
			Course savedCourse6 = courseRepository.save(course6);
			
			User user7 = new User("NN","NN",new HashSet<Course>(){{
                add(savedCourse1);
                add(savedCourse2);
            }});
			User savedUser7 = userRepository.save(user7);
			
			savedUser1.setCourses(new HashSet<Course>(){{
                add(savedCourse1);
                add(savedCourse2);
            }});
			userRepository.save(savedUser1);
			
			savedUser4.setCourses(new HashSet<Course>(){{
                add(savedCourse5);
                add(savedCourse6);
            }});
			userRepository.save(savedUser4);
			
			Activity activity1 = new Activity("aktivnost1", savedCourse2);
			Activity savedActivity1 = activityRepository.save(activity1);
			Activity activity2 = new Activity("aktivnost2");
			activity2.setCourse(savedCourse2);
			Activity savedActivity2 = activityRepository.save(activity2);
			
			Activity activity3 = new Activity("aktivnost3", savedCourse1);
			Activity savedActivity3 = activityRepository.save(activity3);
			Activity activity4 = new Activity("aktivnost4", savedCourse1);
			Activity savedActivity4 = activityRepository.save(activity4);
			Activity activity5 = new Activity("aktivnost5", savedCourse1);
			Activity savedActivity5 = activityRepository.save(activity5);
			Activity activity6 = new Activity("aktivnost6", savedCourse4);
			Activity savedActivity6 = activityRepository.save(activity6);
			
			// fetch all activities
			log.info("Courses found with findAll():");
			log.info("-------------------------------");
			for (Course course : courseRepository.findAll()) {
				log.info(course.toString());
			}
			log.info("");
		};
	}

}