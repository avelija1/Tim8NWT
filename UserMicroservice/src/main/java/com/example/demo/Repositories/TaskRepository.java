package com.example.demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {


}
