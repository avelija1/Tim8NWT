package com.example.demo;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
@EnableDiscoveryClient
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);

	}
	
	

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, TaskRepository taskRepository,
			RoleRepository roleRepository, CourseRepository courseRepository, RabbitTemplate rabbitTemplate, Queue candidateQueue) {

		
		
		return (args) -> {

			rabbitTemplate.convertAndSend(candidateQueue.getName(), "porukaa");
		};
		
		
	}
	
	@Bean
	public Queue candidateQueue() {
		return new Queue("candidates.queue");
	}

	
	public void sendMessage(String candidate) {
		RabbitTemplate rabbitTemplate = null;
		Logger candidateQueue = null;
		rabbitTemplate.convertAndSend(candidateQueue.getName(), candidate);
	}
}