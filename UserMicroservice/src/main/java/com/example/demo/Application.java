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
			Role role1=new Role("Admin");
			Role savedRole1 = roleRepository.save(role1);
			Role role2=new Role("User");
			Role savedRole2 = roleRepository.save(role2);
			User user1 = new User("Imenko","Prezimenko", "imeprezime", "ime@etf.unsa.ba", "123asd",savedRole2,1,2);
			User savedUser1 = userRepository.save(user1);
			User user2 = new User("Selma","Glavić", "sgl1", "sgl1@etf.unsa.ba", "sifra",1,2);
			user2.setRole(savedRole2);
			User savedUser2 = userRepository.save(user2);
			
			User user3 = new User("Admin","Admin", "aaa", "aaa@etf.unsa.ba", "123asd",savedRole1,1,2);
			User savedUser3 = userRepository.save(user3);
			User user4 = new User("Almedin","Velija", "avv", "avv@etf.unsa.ba", "sifff",savedRole2,1,2);
			User savedUser4 = userRepository.save(user4);
			User user5 = new User("Lejla","Bajgorić", "lejj", "lejj@etf.unsa.ba", "123asd",savedRole2,1,2);
			User savedUser5 = userRepository.save(user5);
			User user6 = new User("Amer","Kodžaga", "aak", "aak@etf.unsa.ba", "aak1",savedRole2,1,2);
			User savedUser6 = userRepository.save(user6);
			
			Task task1 = new Task("Zad1", savedUser2);
			Task savedTask1 = taskRepository.save(task1);
			Task task2 = new Task("Zad2");
			task2.setUser(savedUser2);
			Task savedTask2 = taskRepository.save(task2);
			
			Task task3 = new Task("Zad3", savedUser1);
			Task savedTask3 = taskRepository.save(task3);
			Task task4 = new Task("Zad4", savedUser1);
			Task savedTask4 = taskRepository.save(task4);
			Task task5 = new Task("Zad5", savedUser1);
			Task savedTask5 = taskRepository.save(task5);
			Task task6 = new Task("Zad6", savedUser1);
			Task savedTask6 = taskRepository.save(task6);
			
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
			
			User user7 = new User("NN","NN", "nn", "nn@etf.unsa.ba", "nn2",savedRole2,1,2, new HashSet<Course>(){{
                add(savedCourse1);
                add(savedCourse2);
            }});
			User savedUser7 = userRepository.save(user7);
			
			savedUser1.setCourses(new HashSet<Course>(){{
                add(savedCourse1);
                add(savedCourse2);
            }});
			userRepository.save(savedUser1);
			
			savedUser4.setCourses(new HashSet<Course>(){{
                add(savedCourse5);
                add(savedCourse6);
            }});
			userRepository.save(savedUser4);

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