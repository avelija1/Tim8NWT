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
		
			Course course1 = new Course("KURS1", "KOD", 5.0, "OPIS");
			Course course2 = new Course("KURS2", "KOD2", 6.0, "OPIS2");
			//courseRepository.save(course1);
			//courseRepository.save(course2);
			
		//	Activity activity1 = new Activity("Ispit", course1);
			
		//	User user1 = new User("Amer", "Kodzaga", new HashSet<Course>(){{
          //      add(course1);
            //    add(course2);
           // }});
			
			//pošto su povezani, courses se spase zajedno sa user-om u svoj repozitorij
		//	userRepository.save(user1);
			//activityRepository.save(activity1);
			
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