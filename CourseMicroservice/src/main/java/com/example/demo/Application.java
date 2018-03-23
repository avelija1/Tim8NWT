package com.example.demo;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		

			
			courseRepository.save(new Course("KURS1", "KOD", 5.0, "OPIS"));
			courseRepository.save(new Course("KURS2", "KOD2", 6.0, "OPIS2"));
			
			//Potrebno je dodati vezu many to many sa userom
			//userRepository.save(new User("Amer", "Kodzaga"));
			
			//Potrebno je dodati course_id
			//activityRepository.save(new Activity("Ispit"));
			
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