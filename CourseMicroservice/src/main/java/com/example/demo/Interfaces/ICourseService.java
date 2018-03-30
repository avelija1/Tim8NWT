package com.example.demo.Interfaces;

import java.util.List;

import com.example.demo.Models.Course;
import com.example.demo.Models.User;



public interface ICourseService {
 	Course getCourse(long id);
    
    void createCourse(Course course);
     
    void editCourse(long id, Course modifiedCourse);
     
    void deleteCourse(long id);
 
    List<Course> getCourses(); 
   
    void ChangeUserEnrollmentOnCourse(Course course, User user);
}
