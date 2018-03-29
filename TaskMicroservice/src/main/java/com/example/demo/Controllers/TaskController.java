package com.example.demo.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Models.Task;
import com.example.demo.Services.TaskService;

@RestController
public class TaskController {
    
    /*@GetMapping("/task")
    @ResponseBody
    public String task(@RequestParam(value="name", defaultValue="World") String name) {
        return "Test";
    }*/
	@Autowired
	TaskService taskService;
	
	@RequestMapping(value = "/task/", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllTasks() {
		List<Task> tasks = taskService.getTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>> (tasks, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> getTask(@PathVariable("id") long id){
		System.out.println("Fethching Task with id " + id);
		Task task = taskService.getTask(id);
		if (task == null) {
			System.out.println("Task with id " + id + " not found");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder){
		System.out.println("Creating Task " + task.getName());
		taskService.createTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
		System.out.println("Upradting Task " + id);
		Task currentTask = taskService.getTask(id);
		
		if (currentTask == null) {
			System.out.println("Task with id " + id + " not founf");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		
		currentTask.setName(task.getName());
		
		taskService.editTask(id, currentTask);
		return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Task> deleteTask(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Task with id " + id);
		
		Task task = taskService.getTask(id);
		if (task == null) {
			System.out.println("Unable to delete. User with id " + id +" not found");
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		
		taskService.deleteTask(id);
		return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);
	}
}