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
			Role rola=new Role("Admin");
			Role savedRola = roleRepository.save(rola);
			
			Role rola1=new Role("User");
			Role savedRola2 = roleRepository.save(rola1);
			
			User user1 = new User("Imenko","Prezimenko", "imeprezime", "ime@etf.unsa.ba", "123asd",savedRola,1,2);
			
			User user2 = new User("Selma","Glavić", "sgl1", "sgl1@etf.unsa.ba", "sifra",savedRola2,1,2);
			userRepository.save(user1);
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