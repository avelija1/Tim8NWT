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
	public CommandLineRunner demo(CourseRepository courseRepository, UserRepository userRepository,
			ActivityRepository activityRepository) {
		return (args) -> {

		};
	}

}