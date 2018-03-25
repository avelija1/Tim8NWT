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
	public CommandLineRunner demo(ActivityRepository activityRepository) {
		return (args) -> {
		

			ActivityType activityType = new ActivityType("Tip aktivnosti");
			ActivityPlace activityPlace = new ActivityPlace("Zgrada", "Sala");
			activityRepository.save(new Activity("Prva aktivnost", activityType, activityPlace));

			// fetch all activities
			log.info("ActivityTypes found with findAll():");
			log.info("-------------------------------");
			for (Activity activity : activityRepository.findAll()) {
				log.info(activity.toString());
			}
			log.info("");
		};
	}

}