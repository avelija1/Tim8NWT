package com.example.demo;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Models.Course;
import com.example.demo.Models.Role;
import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Repositories.CourseRepository;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Repositories.UserRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository,
			TaskRepository taskRepository,
			RoleRepository roleRepository,
			CourseRepository courseRepository) {
		return (args) -> {
			// save a couple of customers

			Role role1 = new Role("Administrator");
			Role role2 = new Role("Korisnik");
			//roleRepository.save(role1);
			//roleRepository.save(role2);
			User user1 = new User(1,"Imenko","Prezimenko", "imeprezime", "ime@etf.unsa.ba", "123asd",role1,1,2);
			//User user3 = new User("Selma","Glavić", "sgl1", "sgl1@etf.unsa.ba", "sifra",role2,1,2);
			userRepository.save(user1);
			//userRepository.save(user3);
			
			
			Course course1 = new Course("KURS1");
			Course course2 = new Course("KURS2");
			/*
			Course course1 = new Course("Kurs", new HashSet<User>(){{
                add(user1);
                add(user2);
            }});*/
			
			User user2 = new User("Amer","Kodžaga", "akod", "akod@etf.unsa.ba", "sifra2",role2,1,2, new HashSet<Course>(){{
                add(course1);
                add(course2);
            }});
			Task task1 = new Task("Zad1", user2);
			
			taskRepository.save(task1);
			
			userRepository.save(user2);

			// fetch all activities
			log.info("ActivityTypes found with findAll():");
			log.info("-------------------------------");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			log.info("");
		};
	}

}