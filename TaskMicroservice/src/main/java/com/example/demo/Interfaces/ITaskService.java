package com.example.demo.Interfaces;

import java.util.List;

import com.example.demo.Models.Task;

public interface ITaskService {
 	Task getTask(long id);
    
    void createTask(Task task);
     
    void editTask(int id, Task modifiedTask);
     
    void deleteTask(long id);
 
    List<Task> getTasks(); 
}
