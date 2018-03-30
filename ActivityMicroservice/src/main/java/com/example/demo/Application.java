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
	@Transactional
	public CommandLineRunner demo(ActivityRepository activityRepository,
			CourseRepository courseRepository,
			ActivityTypeRepository activityTypeRepository,
			ActivityPlaceRepository activityPlaceRepository) {
		return (args) -> {
		

			//ActivityType activityType = new ActivityType("Tip aktivnosti");
			//ActivityPlace activityPlace = new ActivityPlace("Zgrada", "Sala");
			//Course c=new Course("CourseName","Code",1,"Description");
			//activityRepository.save(new Activity("Prva aktivnost", activityType, activityPlace,c));

			Course course1 = new Course("KURS1");
			Course course2 = new Course("KURS2");
			Course course3 = new Course("KURS3");
			Course course4 = new Course("KURS4");
			Course course5 = new Course("KURS5");
			Course course6 = new Course("KURS6");
			Course savedCourse1 = courseRepository.save(course1);
			Course savedCourse2 = courseRepository.save(course2);
			Course savedCourse3 = courseRepository.save(course3);
			Course savedCourse4 = courseRepository.save(course4);
			Course savedCourse5 = courseRepository.save(course5);
			Course savedCourse6 = courseRepository.save(course6);
			
			Activity activity1 = new Activity("aktivnost1", savedCourse2);
			Activity savedActivity1 = activityRepository.save(activity1);
			Activity activity2 = new Activity("aktivnost2");
			activity2.setCourse(savedCourse2);
			Activity savedActivity2 = activityRepository.save(activity2);
			
			Activity activity3 = new Activity("aktivnost3", savedCourse1);
			Activity savedActivity3 = activityRepository.save(activity3);
			Activity activity4 = new Activity("aktivnost4", savedCourse1);
			Activity savedActivity4 = activityRepository.save(activity4);
			Activity activity5 = new Activity("aktivnost5", savedCourse1);
			Activity savedActivity5 = activityRepository.save(activity5);
			Activity activity6 = new Activity("aktivnost6", savedCourse4);
			Activity savedActivity6 = activityRepository.save(activity6);
			
			
			ActivityType activityType1 = new ActivityType("TIP1");
			ActivityType activityType2 = new ActivityType("TIP2");
			ActivityType activityType3 = new ActivityType("TIP3");
			ActivityType activityType4 = new ActivityType("TIP4");
			ActivityType activityType5 = new ActivityType("TIP5");
			ActivityType activityType6 = new ActivityType("TIP6");
			ActivityType savedActivityType1 = activityTypeRepository.save(activityType1);
			ActivityType savedActivityType2 = activityTypeRepository.save(activityType2);
			ActivityType savedActivityType3 = activityTypeRepository.save(activityType3);
			ActivityType savedActivityType4 = activityTypeRepository.save(activityType4);
			ActivityType savedActivityType5 = activityTypeRepository.save(activityType5);
			ActivityType savedActivityType6 = activityTypeRepository.save(activityType6);
			
			ActivityPlace activityPlace1 = new ActivityPlace("Zgrada1", "Sala1");
			ActivityPlace activityPlace2 = new ActivityPlace("Zgrada2", "Sala2");
			ActivityPlace activityPlace3 = new ActivityPlace("Zgrada3", "Sala3");
			ActivityPlace activityPlace4 = new ActivityPlace("Zgrada4", "Sala4");
			ActivityPlace activityPlace5 = new ActivityPlace("Zgrada5", "Sala5");
			ActivityPlace activityPlace6 = new ActivityPlace("Zgrada6", "Sala6");
			ActivityPlace savedActivityPlace1 = activityPlaceRepository.save(activityPlace1);
			ActivityPlace savedActivityPlace2 = activityPlaceRepository.save(activityPlace2);
			ActivityPlace savedActivityPlace3 = activityPlaceRepository.save(activityPlace3);
			ActivityPlace savedActivityPlace4 = activityPlaceRepository.save(activityPlace4);
			ActivityPlace savedActivityPlace5 = activityPlaceRepository.save(activityPlace5);
			ActivityPlace savedActivityPlace6 = activityPlaceRepository.save(activityPlace6);
			
			savedActivity1.setActivityPlace(savedActivityPlace6);
			savedActivity1.setActivityType(savedActivityType6);
			activityRepository.save(savedActivity1);
			
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
