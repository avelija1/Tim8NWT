package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.Interfaces.ICourseService;
import com.example.demo.Models.Course;

@Service("courseService")
@Transactional
public class CourseService implements ICourseService {

	@Override
	public Course getCourse(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editCourse(int id, Course modifiedCourse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourse(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ChangeUserEnrollmentOnCourse() {
		// TODO Auto-generated method stub
		
	}

}
