package com.example.demo;


import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Models.Task;
import com.example.demo.Models.User;
import com.example.demo.Repositories.TaskRepository;
import com.example.demo.Repositories.UserRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(TaskRepository taskRepository, 
			UserRepository userRepository) {
		return (args) -> {
		
			User user1 = new User("Lejla", "Bajgorić");
			User user2 = new User("Selma", "Glavić");
			User user3 = new User("Almedin", "Velija");
			User user4 = new User("Amer", "Kodžaga");
			//userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			
			Date datum = new Date();
			Task task1 = new Task("zadatak1", "Bilješke", datum, true, user1);
			
			taskRepository.save(task1);
			
			// fetch all activities
			log.info("Courses found with findAll():");
			log.info("-------------------------------");
			for (Task task : taskRepository.findAll()) {
				log.info(task.toString());
			}
			log.info("");
		};
	}

}