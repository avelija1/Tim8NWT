package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Models.Course;
@CrossOrigin(origins = "http://localhost:4200")
public interface CourseRepository extends CrudRepository<Course, Long> {


}
