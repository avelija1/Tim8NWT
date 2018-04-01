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
			
			
			/*
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
			
			Task task1 = new Task("Zad1", "bilješke", new Date(), true, savedUser2);
			Task savedTask1 = taskRepository.save(task1);
			Task task2 = new Task("Zad2", "zabilježeno", new Date(), false);
			task2.setUser(savedUser2);
			Task savedTask2 = taskRepository.save(task2);
			
			Task task3 = new Task("Zad3", "sdaerwetsfs", new Date(), false, savedUser1);
			Task savedTask3 = taskRepository.save(task3);
			Task task4 = new Task("Zad4", "sfaefef", new Date(), true, savedUser1);
			Task savedTask4 = taskRepository.save(task4);
			Task task5 = new Task("Zad5", "bbbbbbb", new Date(), false, savedUser1);
			Task savedTask5 = taskRepository.save(task5);
			Task task6 = new Task("Zad6", "ništa", new Date(), false, savedUser1);
			Task savedTask6 = taskRepository.save(task6);
			
			User user7 = new User("NN","NN");
			User savedUser7 = userRepository.save(user7);
			
			// fetch all activities
			log.info("Courses found with findAll():");
			log.info("-------------------------------");
			for (Task task : taskRepository.findAll()) {
				log.info(task.toString());
			}
			log.info("");*/
		};
	}

}