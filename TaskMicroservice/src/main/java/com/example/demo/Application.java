package com.example.demo;

import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.example.demo.Models.Task;
import com.example.demo.Models.User;
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
	@Transactional
	public CommandLineRunner demo(TaskRepository taskRepository, UserRepository userRepository) {
		return (args) -> {

		};
	}
	
	@Bean
	public Queue candidateQueue() {
		return new Queue("candidates.queue");
	}

	@RabbitListener(queues = "#{candidateQueue.name}")
	public void getCandidateMessage(String candidateMessage) {
		log.info(candidateMessage);

		//for (int kk = 1; kk < 5; kk++)
			log.info("porukaa");
		
	}

}