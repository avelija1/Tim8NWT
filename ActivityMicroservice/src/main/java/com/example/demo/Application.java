package com.example.demo;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.Models.Activity;
import com.example.demo.Models.ActivityPlace;
import com.example.demo.Models.ActivityType;
import com.example.demo.Models.Course;
import com.example.demo.Repositories.ActivityPlaceRepository;
import com.example.demo.Repositories.ActivityRepository;
import com.example.demo.Repositories.ActivityTypeRepository;
import com.example.demo.Repositories.CourseRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(ActivityRepository activityRepository, CourseRepository courseRepository,
			ActivityTypeRepository activityTypeRepository, ActivityPlaceRepository activityPlaceRepository) {

		return (args) -> {
		};
	}
}
