package com.example.demo.Controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Models.Course;
import com.example.demo.Models.User;
import com.example.demo.Services.CourseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

	@Autowired
	CourseService courseService;

	@RequestMapping(value = "/course/", method = RequestMethod.GET)
	public ResponseEntity<List<Course>> getCourses() {
		List<Course> courses = courseService.getCourses();
		courses.add(new Course("testCourse","testCode",5,"This is test course"));
		if (courses.isEmpty()) {
			return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> getCourse(@PathVariable("id") long id) {
		System.out.println("Fethching Course with id " + id);
		Course course = courseService.getCourse(id);
		if (course == null) {
			System.out.println("Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
		courseService.createCourse(course);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Course> updateCourse(@PathVariable("id") long id, @RequestBody Course course) {
		Course currentCourse = courseService.getCourse(id);

		if (currentCourse == null) {
			System.out.println("Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}

		courseService.editCourse(id, course);
		return new ResponseEntity<Course>(currentCourse, HttpStatus.OK);
	}

	@RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteCourse(@PathVariable("id") long id) {
		System.out.println("Fethcing & Deleting Course with id " + id);
		Course course = courseService.getCourse(id);
		if (course == null) {
			System.out.println("Unable to delete. Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}

		courseService.deleteCourse(id);
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}

}
