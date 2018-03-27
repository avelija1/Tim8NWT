package com.example.demo.Interfaces;

import java.util.List;

import com.example.demo.Models.Course;



public interface ICourseService {
 	Course getCourse(long id);
    
    void createCourse(Course course);
     
    void editCourse(int id, Course modifiedCourse);
     
    void deleteCourse(long id);
 
    List<Course> getCourses(); 
   
    void ChangeUserEnrollmentOnCourse();
}
