package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Models.Task;
@CrossOrigin(origins = "http://localhost:4200")
public interface TaskRepository extends CrudRepository<Task, Long> {


}
